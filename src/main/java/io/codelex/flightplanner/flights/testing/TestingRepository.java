package io.codelex.flightplanner.flights.testing;

import io.codelex.flightplanner.FlightPlannerApplication;
import io.codelex.flightplanner.flights.domain.Flights;
import org.springframework.stereotype.Repository;

@Repository
public class TestingRepository {
    public Flights flights = FlightPlannerApplication.repository;

    public void clearFlights(){
        flights.clear();
    }
}
