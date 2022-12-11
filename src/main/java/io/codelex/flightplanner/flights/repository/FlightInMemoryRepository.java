package io.codelex.flightplanner.flights.repository;

import io.codelex.flightplanner.flights.domain.Flight;
import io.codelex.flightplanner.flights.dto.PageResult;
import io.codelex.flightplanner.flights.dto.SearchFlightRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Repository
//@ConditionalOnProperty(prefix = "myapp", name = "data", havingValue = "inmemory")
public class FlightInMemoryRepository {
    private List<Flight> flights = new ArrayList<>();

    public List<Flight> getAllFlights() {
        return flights;
    }

    public void clearFlights() {
        flights.clear();
    }

    public ResponseEntity<Flight> addFlight(Flight flight) throws ParseException {
        if (checkUnique(flight)) {
            flights.add(flight);
            return new ResponseEntity<>(flight, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(flight, HttpStatus.CONFLICT);
    }

    public ResponseEntity<Flight> deleteFlight(int id) {
        flights.removeIf(f -> f.getId() == id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    public ResponseEntity<PageResult<Flight>> searchFlights(SearchFlightRequest searchFlightRequest) {
        PageResult pageResult = new PageResult();
        for (Flight flight : flights) {
            if (searchFlightRequest.getFrom().equalsIgnoreCase(flight.getFrom().getAirport()) &&
                    searchFlightRequest.getTo().equalsIgnoreCase(flight.getTo().getAirport()) &&
                    flight.getDepartureTime().getYear() == searchFlightRequest.getDepartureTime().getYear() &&
                    flight.getDepartureTime().getMonth() == searchFlightRequest.getDepartureTime().getMonth() &&
                    flight.getDepartureTime().getDayOfMonth() == searchFlightRequest.getDepartureTime().getDayOfMonth()) {
                pageResult.addItem(flight);
            }
        }
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    private boolean checkUnique(Flight flight) {
        for (Flight flightsExisting : flights) {
            if (flightsExisting.getFrom().getAirport().equalsIgnoreCase(flight.getFrom().getAirport()) &&
                    flightsExisting.getTo().getAirport().equalsIgnoreCase(flight.getTo().getAirport()) &&
                    flightsExisting.getDepartureTime().equals(flight.getDepartureTime()) &&
                    flightsExisting.getArrivalTime().equals(flight.getArrivalTime()) &&
                    flightsExisting.getCarrier().equals(flight.getCarrier())) {
                return false;
            }
        }
        return true;
    }
}
