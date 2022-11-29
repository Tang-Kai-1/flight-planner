package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.flights.domain.*;
import io.codelex.flightplanner.flights.dto.FlightRequest;
import io.codelex.flightplanner.flights.dto.PageResult;
import io.codelex.flightplanner.flights.dto.SearchFlightRequest;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.util.List;

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
                airportRepository.
                flightRepository.saveAndFlush(flight);
                //airportRepository.saveAndFlush(flight.getFrom());
                //airportRepository.saveAndFlush(flight.getTo());

                return new ResponseEntity<>(flight, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(flight, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(flight, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Flight> fetchFlight(int id) {
        if (!flightRepository.existsById(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flightRepository.getReferenceById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Flight> deleteFlight(int id) {
        if(flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Airport[]> searchAirports(String search) {
        if (airportRepository.existsByAirportOrCountryOrCityLikeIgnoreCase(search, search, search)){
            Airport found = airportRepository.findFirstByAirportOrCountryOrCityLikeIgnoreCase(search, search, search);
            return new ResponseEntity<>(new Airport[]{found}, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<PageResult<Flight>> searchFlights(SearchFlightRequest searchFlightRequest) {
        return null;
    }

    @Override
    public void clearFlights() {
        flightRepository.deleteAllInBatch();
        airportRepository.deleteAllInBatch();
    }

    private boolean checkUnique(Flight flight){
        List<Flight> flightList = flightRepository.findAll();
        for(Flight flightsExisting: flightList){
            if(!airportRepository.existsById(flight.getFrom().getAirport()) || !airportRepository.existsById(flight.getTo().getAirport())){
                continue;
            }
            Airport flightFrom = airportRepository.getReferenceById(flight.getFrom().getAirport());
            Airport flightTo = airportRepository.getReferenceById(flight.getTo().getAirport());
            if(flightFrom.equals(flight.getFrom())
                    && flightTo.equals(flight.getTo()) &&
            flightsExisting.getCarrier().equalsIgnoreCase(flight.getCarrier()) && flightsExisting.getDepartureTime().equals(flight.getDepartureTime()) &&
            flightsExisting.getArrivalTime().equals(flight.getArrivalTime())){
                return false;
            }
        }
        return true;
    }
}