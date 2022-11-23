package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.flights.domain.Flight;
import io.codelex.flightplanner.flights.dto.PageResult;
import io.codelex.flightplanner.flights.dto.SearchFlightRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@ConditionalOnProperty(prefix = "myapp", name = "data", havingValue = "inmemory")
public class FlightDBRepository implements FlightRepository {
    @Override
    public List<Flight> getAllFlights() {
        return null;
    }

    @Override
    public void clearFlights() {

    }

    @Override
    public ResponseEntity<Flight> addFlight(Flight flight) {
        return null;
    }

    @Override
    public ResponseEntity<Flight> deleteFlight(int id) {
        return null;
    }

    @Override
    public ResponseEntity<PageResult<Flight>> searchFlights(SearchFlightRequest searchFlightRequest) {
        return null;
    }
}
