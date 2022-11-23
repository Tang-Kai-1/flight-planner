/*
package io.codelex.flightplanner.flights.admin;

import io.codelex.flightplanner.flights.domain.Flight;
import io.codelex.flightplanner.flights.dto.FlightRequest;
import io.codelex.flightplanner.flights.response.NullException;
import io.codelex.flightplanner.flights.response.SameFromToException;
import io.codelex.flightplanner.flights.response.TimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class AdminService {
    private AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public ResponseEntity<Flight> addFlight(FlightRequest flightRequest) throws ParseException, TimeException, NullException, SameFromToException {
        return adminRepository.addFlight(flightRequest);
    }

    public ResponseEntity<Flight> fetchFlight(int id) {
        return adminRepository.fetchFlight(id);
    }

    public ResponseEntity<Flight> deleteFlight(int id) {
        return adminRepository.deleteFlight(id);
    }
}
*/