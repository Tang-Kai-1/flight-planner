package io.codelex.flightplanner.flights.repository;

import io.codelex.flightplanner.flights.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, String> {

}
