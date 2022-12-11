package io.codelex.flightplanner.flights.repository;

import io.codelex.flightplanner.flights.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {


}
