package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.flights.domain.*;
import io.codelex.flightplanner.flights.dto.FlightRequest;
import io.codelex.flightplanner.flights.dto.PageResult;
import io.codelex.flightplanner.flights.dto.SearchFlightRequest;
import io.codelex.flightplanner.flights.response.NullException;
import io.codelex.flightplanner.flights.response.SameFromToException;
import io.codelex.flightplanner.flights.response.TimeException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@ConditionalOnProperty(prefix = "myapp", name = "appmode", havingValue = "db")
public class FlightDBService implements FlightService {

    private FlightDBRepository repository;

    public FlightDBService(FlightDBRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Flight> addFlight(FlightRequest flightRequest) throws ParseException, TimeException, NullException, SameFromToException {
        return null;
    }

    @Override
    public ResponseEntity<Flight> fetchFlight(int id) {
        return null;
    }

    @Override
    public ResponseEntity<Flight> deleteFlight(int id) {
        return null;
    }

    @Override
    public ResponseEntity<Airport[]> searchAirports(String search) {
        return null;
    }

    @Override
    public ResponseEntity<PageResult<Flight>> searchFlights(SearchFlightRequest searchFlightRequest) throws NullException, SameFromToException {
        return null;
    }

    @Override
    public void clearFlights() {

    }
}