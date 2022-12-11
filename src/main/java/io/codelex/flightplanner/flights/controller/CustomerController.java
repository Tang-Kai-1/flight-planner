package io.codelex.flightplanner.flights.controller;

import io.codelex.flightplanner.flights.domain.*;
import io.codelex.flightplanner.flights.dto.PageResult;
import io.codelex.flightplanner.flights.dto.SearchFlightRequest;
import io.codelex.flightplanner.flights.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private FlightService service;

    public CustomerController(FlightService customerService) {
        this.service = customerService;
    }

    @GetMapping("/airports")
    public ResponseEntity<Airport[]> searchAirports(@RequestParam String search) {
        return service.searchAirports(search);
    }

    @PostMapping("/flights/search")
    public ResponseEntity<PageResult<Flight>> searchFlights(@RequestBody SearchFlightRequest searchFlightRequest) {
        return service.searchFlights(searchFlightRequest);
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> findFlightById(@PathVariable("id") int id) {
        return service.fetchFlight(id);
    }
}
