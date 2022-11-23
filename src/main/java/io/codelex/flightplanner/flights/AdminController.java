package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.flights.domain.Flight;
import io.codelex.flightplanner.flights.dto.FlightRequest;
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
