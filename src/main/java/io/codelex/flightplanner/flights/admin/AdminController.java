package io.codelex.flightplanner.flights.admin;

import io.codelex.flightplanner.flights.domain.Flight;
import io.codelex.flightplanner.flights.domain.FlightRequest;
import io.codelex.flightplanner.flights.response.NullException;
import io.codelex.flightplanner.flights.response.SameFromToException;
import io.codelex.flightplanner.flights.response.TimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
//@RequestMapping(value="/admin-api",method = {RequestMethod.GET, RequestMethod.PUT})//, RequestMethod.DELETE})//, produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/admin-api")
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping("/flights")
    public ResponseEntity<Flight> addFlight(@RequestBody FlightRequest flightRequest) throws ParseException, TimeException, NullException, SameFromToException {
        return adminService.addFlight(flightRequest);
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> fetchFlight(@PathVariable("id") int id) {
        return adminService.fetchFlight(id);
    }

    @DeleteMapping("/flights/{id}")
    public ResponseEntity<Flight> deleteFlight(@PathVariable("id") int id) {
        return adminService.deleteFlight(id);
    }
}
