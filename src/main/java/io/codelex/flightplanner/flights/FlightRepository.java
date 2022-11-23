package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.flights.domain.Flight;
import io.codelex.flightplanner.flights.dto.PageResult;
import io.codelex.flightplanner.flights.dto.SearchFlightRequest;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface FlightRepository {
    List<Flight> getAllFlights();

    void clearFlights();

    ResponseEntity<Flight> addFlight(Flight flight) throws ParseException;

    ResponseEntity<Flight> deleteFlight(int id);

    ResponseEntity<PageResult<Flight>> searchFlights(SearchFlightRequest searchFlightRequest);

}
