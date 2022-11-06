package io.codelex.flightplanner.flights.customer;

import io.codelex.flightplanner.flights.domain.*;
import io.codelex.flightplanner.flights.response.NullException;
import io.codelex.flightplanner.flights.response.SameFromToException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<Airport[]> searchAirports(String search) {
        return customerRepository.searchAirports(search);
    }

    public ResponseEntity<PageResult<Flight>> searchFlights(SearchFlightRequest searchFlightRequest) throws NullException, SameFromToException {

        if (searchFlightRequest.checkValidity()) {
            if (searchFlightRequest.getFrom().equalsIgnoreCase(searchFlightRequest.getTo())) {
                throw new SameFromToException("Cannot have same airport as departure and destination point for single flight");
            } else {
                return customerRepository.searchFlights(searchFlightRequest);
            }
        } else {
            throw new NullException("Cannot have empty fields");
        }
    }

    public ResponseEntity<Flight> findFlightById(int id) {
        return customerRepository.findFlightById(id);
    }
}
