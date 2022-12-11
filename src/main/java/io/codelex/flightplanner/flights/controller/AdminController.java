package io.codelex.flightplanner.flights.controller;

import io.codelex.flightplanner.flights.domain.Flight;
import io.codelex.flightplanner.flights.dto.FlightRequest;
import io.codelex.flightplanner.flights.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/admin-api")
public class AdminController {

    private FlightService service;

    public AdminController(FlightService service) {
        this.service = service;
    }

    @PutMapping("/flights")
    public ResponseEntity<Flight> addFlight(@RequestBody FlightRequest flightRequest) throws ParseException {
        System.out.println("Received flight request");
        System.out.println("Departure:");
        System.out.println(flightRequest.getDepartureTime());
        System.out.println("Arrival:");
        System.out.println(flightRequest.getArrivalTime());
        return service.addFlight(flightRequest);
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> fetchFlight(@PathVariable("id") int id) {
        return service.fetchFlight(id);
    }

    @DeleteMapping("/flights/{id}")
    public ResponseEntity<Flight> deleteFlight(@PathVariable("id") int id) {
        return service.deleteFlight(id);
    }
}
