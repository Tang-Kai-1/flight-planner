package io.codelex.flightplanner.flights.service;

import io.codelex.flightplanner.flights.domain.*;
import io.codelex.flightplanner.flights.dto.FlightRequest;
import io.codelex.flightplanner.flights.dto.PageResult;
import io.codelex.flightplanner.flights.dto.SearchFlightRequest;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

public interface FlightService {
    ResponseEntity<Flight> addFlight(FlightRequest flightRequest) throws ParseException;

    ResponseEntity<Flight> fetchFlight(int id);

    ResponseEntity<Flight> deleteFlight(int id);

    ResponseEntity<Airport[]> searchAirports(String search);

    ResponseEntity<PageResult<Flight>> searchFlights(SearchFlightRequest searchFlightRequest);

    void clearFlights();
}
