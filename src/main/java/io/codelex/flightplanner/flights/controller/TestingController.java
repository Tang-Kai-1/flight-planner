package io.codelex.flightplanner.flights.controller;

import io.codelex.flightplanner.flights.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/testing-api", method = {RequestMethod.PUT})
public class TestingController {

    private FlightService service;

    public TestingController(FlightService testingService) {
        this.service = testingService;
    }

    @PostMapping("/clear")
    @ResponseStatus(HttpStatus.OK)
    public void clearFlights() {
        service.clearFlights();
    }
}
