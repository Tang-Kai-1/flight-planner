package io.codelex.flightplanner.flights;

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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@ConditionalOnProperty(prefix = "myapp", name = "appmode", havingValue = "inmemory")
public class FlightInMemoryService implements FlightService {

    private FlightInMemoryRepository repository;
    //private FlightRepository repository   --nestrādāja, jo nespēja autowierot 
    public FlightInMemoryService(FlightInMemoryRepository repository) {
        this.repository = repository;
    }

    private AtomicInteger id = new AtomicInteger(1);

    @Override
    public ResponseEntity<Flight> addFlight(FlightRequest flightRequest) throws ParseException {
        ResponseEntity<Flight> response = repository.addFlight(new Flight(id.intValue(), flightRequest));
        if (response.getStatusCode().is2xxSuccessful()) {
            id.incrementAndGet();
        }
        return response;
    }

    @Override
    public ResponseEntity<Flight> fetchFlight(int id) {
        Flight flight = repository.getAllFlights().stream().filter(f -> f.getId() == id).findAny().orElse(null);
        if (flight == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Flight> deleteFlight(int id) {
        return repository.deleteFlight(id);
    }

    @Override
    public ResponseEntity<Airport[]> searchAirports(String search) {
        List<Flight> flights = repository.getAllFlights();
        Airport[] airport = new Airport[]{flights.stream().filter(f ->
                f.getFrom().getCity().toLowerCase().startsWith(search.toLowerCase().trim()) ||
                        f.getFrom().getCountry().toLowerCase().startsWith(search.toLowerCase().trim()) ||
                        f.getFrom().getAirport().toLowerCase().startsWith(search.toLowerCase().trim())).map(Flight::getFrom).findFirst().orElse(
                flights.stream().filter(
                                f -> f.getTo().getCity().toLowerCase().startsWith(search.toLowerCase().trim()) ||
                                        f.getTo().getCountry().toLowerCase().startsWith(search.toLowerCase().trim()) ||
                                        f.getTo().getAirport().toLowerCase().startsWith(search.toLowerCase().trim()))
                        .map(Flight::getTo).findFirst().orElse(null))
        };
        if (Arrays.stream(airport).anyMatch(a -> a == null)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(airport, HttpStatus.OK);
        /*
        for (Flight flight : repository.getAllFlights()) {
            Airport airportFrom = flight.getFrom();
            Airport airportTo = flight.getTo();
            if (airportFrom.getCity().toLowerCase().startsWith(search.toLowerCase().trim()) ||
                    airportFrom.getCountry().toLowerCase().startsWith(search.toLowerCase().trim()) ||
                    airportFrom.getAirport().toLowerCase().startsWith(search.toLowerCase().trim())) {
                return new ResponseEntity<>(new Airport[]{airportFrom}, HttpStatus.OK);
            }
            if (airportTo.getCity().toLowerCase().startsWith(search.toLowerCase().trim()) ||
                    airportTo.getCountry().toLowerCase().startsWith(search.toLowerCase().trim()) ||
                    airportTo.getAirport().toLowerCase().startsWith(search.toLowerCase().trim())) {
                return new ResponseEntity<>(new Airport[]{airportTo}, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        */
    }

    @Override
    public ResponseEntity<PageResult<Flight>> searchFlights(SearchFlightRequest searchFlightRequest) {
        if (searchFlightRequest.checkValidity()) {
            if (searchFlightRequest.getFrom().equalsIgnoreCase(searchFlightRequest.getTo())) {
                throw new SameFromToException("Cannot have same airport as departure and destination point for single flight");
            } else {
                return repository.searchFlights(searchFlightRequest);
            }
        } else {
            throw new NullException("Cannot have empty fields");
        }
    }

    @Override
    public void clearFlights() {
        repository.clearFlights();
    }
}
