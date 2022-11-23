/*
package io.codelex.flightplanner.flights.domain;


import io.codelex.flightplanner.flights.dto.PageResult;
import io.codelex.flightplanner.flights.dto.SearchFlightRequest;
import io.codelex.flightplanner.flights.response.NullException;
import io.codelex.flightplanner.flights.response.SameFromToException;
import io.codelex.flightplanner.flights.response.TimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Flights {
    List<Flight> flights = new ArrayList<>();

    public ResponseEntity<Flight> addFlight(Flight flight) throws NullException, TimeException, ParseException, SameFromToException {
        if (flight.checkValidity()) {
            if (checkUnique(flight)) {
                flights.add(flight);
                return new ResponseEntity<>(flight, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(flight, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(flight, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Flight> fetchFlight(int id) {
        for (Flight flight : flights) {
            if (flight.getId() == id) {
                return new ResponseEntity<>(flight, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Flight> deleteFlight(int id) {
        Flight removedFlight = null;
        for (Flight flight : flights) {
            if (flight.getId() == id) {
                removedFlight = flight;
                break;
            }
        }
        if (removedFlight != null) {
            flights.remove(removedFlight);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    public void clear() {
        flights.clear();
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

    public ResponseEntity<Airport[]> searchAirports(String search) {
        for (Flight flight : flights) {
            Airport airportFrom = flight.getFrom();
            Airport airportTo = flight.getTo();
            if (airportFrom.getCity().toLowerCase().startsWith(search.toLowerCase().trim()) ||
                    airportFrom.getCountry().toLowerCase().startsWith(search.toLowerCase().trim()) ||
                    airportFrom.getAirport().toLowerCase().startsWith(search.toLowerCase().trim())) {
                return new ResponseEntity<>(new Airport[]{airportFrom}, HttpStatus.OK);
            }
            if (airportTo.getCity().toLowerCase().startsWith(search.toLowerCase().trim()) ||
                    airportTo.getCountry().toLowerCase().startsWith(search.toLowerCase().trim()) ||
                    airportTo.getAirport().toLowerCase().startsWith(search.toLowerCase().trim())) {
                return new ResponseEntity<>(new Airport[]{airportTo}, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<PageResult<Flight>> searchFlights(SearchFlightRequest searchFlightRequest) {
        PageResult pageResult = new PageResult();
        for (Flight flight : flights) {
            if (searchFlightRequest.getFrom().equalsIgnoreCase(flight.getFrom().getAirport()) &&
                    searchFlightRequest.getTo().equalsIgnoreCase(flight.getTo().getAirport()) &&
                    searchFlightRequest.getDepartureTime().equals(flight.getArrivalTime())
            ) {
                pageResult.addItem(flight);
            }
        }
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    public ResponseEntity<Flight> findFlightById(int id) {
        for (Flight flight : flights) {
            if (flight.getId() == id) {
                return new ResponseEntity<>(flight, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
*/