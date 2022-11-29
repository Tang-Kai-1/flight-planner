package io.codelex.flightplanner.flights.repository;

import io.codelex.flightplanner.flights.domain.Flight;
import io.codelex.flightplanner.flights.dto.PageResult;
import io.codelex.flightplanner.flights.dto.SearchFlightRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {


}
