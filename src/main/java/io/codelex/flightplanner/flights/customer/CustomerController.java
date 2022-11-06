package io.codelex.flightplanner.flights.customer;

import io.codelex.flightplanner.flights.domain.*;
import io.codelex.flightplanner.flights.response.NullException;
import io.codelex.flightplanner.flights.response.SameFromToException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/airports")
    public ResponseEntity<Airport[]> searchAirports(@RequestParam String search) {
        return customerService.searchAirports(search);
    }

    @PostMapping("/flights/search")
    public ResponseEntity<PageResult<Flight>> searchFlights(@RequestBody SearchFlightRequest searchFlightRequest) throws NullException, SameFromToException {
        return customerService.searchFlights(searchFlightRequest);
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> findFlightById(@PathVariable("id") int id) {
        return customerService.findFlightById(id);
    }
}
