/*
package io.codelex.flightplanner.flights.customer;

import io.codelex.flightplanner.FlightPlannerApplication;
import io.codelex.flightplanner.flights.domain.*;
import io.codelex.flightplanner.flights.dto.PageResult;
import io.codelex.flightplanner.flights.dto.SearchFlightRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {
    private Flights repository;

    public CustomerRepository() {
        this.repository = FlightPlannerApplication.repository;
    }

    public ResponseEntity<Airport[]> searchAirports(String search) {
        return repository.searchAirports(search);
    }

    public ResponseEntity<PageResult<Flight>> searchFlights(SearchFlightRequest searchFlightRequest) {
        return repository.searchFlights(searchFlightRequest);
    }

    public ResponseEntity<Flight> findFlightById(int id) {
        return repository.findFlightById(id);
    }
}
*/