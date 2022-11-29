package io.codelex.flightplanner.flights.service;

import io.codelex.flightplanner.flights.repository.AirportRepository;
import io.codelex.flightplanner.flights.repository.FlightRepository;
import io.codelex.flightplanner.flights.domain.*;
import io.codelex.flightplanner.flights.dto.FlightRequest;
import io.codelex.flightplanner.flights.dto.PageResult;
import io.codelex.flightplanner.flights.dto.SearchFlightRequest;
import io.codelex.flightplanner.flights.response.NullException;
import io.codelex.flightplanner.flights.response.SameFromToException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.text.ParseException;

@Service
@ConditionalOnProperty(prefix = "myapp", name = "appmode", havingValue = "db")
public class FlightDBService implements FlightService {

    private FlightRepository flightRepository;
    private AirportRepository airportRepository;

    public FlightDBService(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    public ResponseEntity<Flight> addFlight(FlightRequest flightRequest) throws ParseException {
        Flight flight = new Flight(flightRequest);
        if (flight.checkValidity()) {
            if (checkUnique(flight)) {
                flight.setFrom(findOrCreate(flight.getFrom()));
                flight.setTo(findOrCreate(flight.getTo()));
                flightRepository.saveAndFlush(flight);
                return new ResponseEntity<>(flight, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(flight, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(flight, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Flight> fetchFlight(int id) {
        Flight flight = flightRepository.findById(id).orElse(null);
        if (flight == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Flight> deleteFlight(int id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Airport[]> searchAirports(String search) {
        search = search.trim().toLowerCase();
        for (Airport airport : airportRepository.findAll()) {
            if (airport.getAirport().toLowerCase().startsWith(search)
                    || airport.getCity().toLowerCase().startsWith(search)
                    || airport.getCountry().toLowerCase().startsWith(search)) {
                return new ResponseEntity<>(new Airport[]{airport}, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<PageResult<Flight>> searchFlights(SearchFlightRequest searchFlightRequest) {
        if (searchFlightRequest.checkValidity()) {
            if (searchFlightRequest.getFrom().equalsIgnoreCase(searchFlightRequest.getTo())) {
                throw new SameFromToException("Cannot have same airport as departure and destination point for single flight");
            } else {
                return findFlights(searchFlightRequest);
            }
        } else {
            throw new NullException("Cannot have empty fields");
        }
    }

    @Override
    public void clearFlights() {
        flightRepository.deleteAllInBatch();
        airportRepository.deleteAllInBatch();
    }

    private boolean checkUnique(Flight flight) {
        Airport flightFrom = airportRepository.findById(flight.getFrom().getAirport()).orElse(null);
        Airport flightTo = airportRepository.findById(flight.getTo().getAirport()).orElse(null);
        for (Flight flightsExisting : flightRepository.findAll()) {
            if (flightFrom == null || flightTo == null) {
                return true;
            }
            if (flightFrom.equals(flight.getFrom()) && flightTo.equals(flight.getTo()) &&
                    flightsExisting.getCarrier().equalsIgnoreCase(flight.getCarrier()) &&
                    flightsExisting.getDepartureTime().equals(flight.getDepartureTime()) &&
                    flightsExisting.getArrivalTime().equals(flight.getArrivalTime())) {
                return false;
            }
        }
        return true;
    }

    private Airport findOrCreate(Airport airport) {
        if (airportRepository.existsById(airport.getAirport())) {
            return airportRepository.getReferenceById(airport.getAirport());
        }
        return airportRepository.saveAndFlush(airport);
    }

    private ResponseEntity<PageResult<Flight>> findFlights(SearchFlightRequest searchFlightRequest) {
        PageResult pageResult = new PageResult();
        for (Flight flight : flightRepository.findAll()) {
            if (searchFlightRequest.getFrom().equalsIgnoreCase(flight.getFrom().getAirport()) &&
                    searchFlightRequest.getTo().equalsIgnoreCase(flight.getTo().getAirport()) &&
                    flight.getDepartureTime().startsWith(searchFlightRequest.getDepartureTime())
            ) {
                pageResult.addItem(flight);
            }
        }
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }
}