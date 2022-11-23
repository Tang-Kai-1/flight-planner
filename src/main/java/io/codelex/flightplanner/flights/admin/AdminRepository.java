/*
package io.codelex.flightplanner.flights.admin;

import io.codelex.flightplanner.FlightPlannerApplication;
import io.codelex.flightplanner.flights.domain.Flight;
import io.codelex.flightplanner.flights.dto.FlightRequest;
import io.codelex.flightplanner.flights.domain.Flights;
import io.codelex.flightplanner.flights.response.NullException;
import io.codelex.flightplanner.flights.response.SameFromToException;
import io.codelex.flightplanner.flights.response.TimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AdminRepository {

    private Flights flightList = FlightPlannerApplication.repository;
    private AtomicInteger id;

    public AdminRepository() {
        id = new AtomicInteger(1);
    }

    public ResponseEntity<Flight> addFlight(FlightRequest flightRequest) throws ParseException, TimeException, NullException, SameFromToException {
        Flight flight = new Flight(id.intValue(), flightRequest);
        ResponseEntity<Flight> ent = flightList.addFlight(flight);
        if (ent.getStatusCode().is2xxSuccessful()) {
            id.incrementAndGet();
        }
        return ent;
    }

    public ResponseEntity<Flight> fetchFlight(int id) {
        return flightList.fetchFlight(id);
    }

    public ResponseEntity<Flight> deleteFlight(int id) {
        return flightList.deleteFlight(id);
    }
}
*/