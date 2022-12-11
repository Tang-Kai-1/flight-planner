package io.codelex.flightplanner.flights.service;

import io.codelex.flightplanner.flights.repository.FlightInMemoryRepository;
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

    //private FlightInMemoryRepository repository;
    private FlightInMemoryRepository repository;   //--nestrādāja, jo nespēja autowierot

    public FlightInMemoryService(FlightInMemoryRepository repository) {
        this.repository = repository;
    }

    private AtomicInteger id = new AtomicInteger(1);

    @Override
    public ResponseEntity<Flight> addFlight(FlightRequest flightRequest) throws ParseException {
        if (flightRequest.checkValidity()) {
            ResponseEntity<Flight> response = repository.addFlight(new Flight(id.intValue(), flightRequest));
            if (response.getStatusCode().is2xxSuccessful()) {
                id.incrementAndGet();
            }
            return response;
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
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
