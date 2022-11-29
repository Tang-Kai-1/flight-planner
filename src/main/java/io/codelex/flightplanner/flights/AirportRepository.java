package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.flights.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, String> {
    boolean existsByAirportOrCountryOrCityLikeIgnoreCase(String airport, String country, String city);
    Airport findFirstByAirportOrCountryOrCityLikeIgnoreCase(String airport, String country, String city);
}
