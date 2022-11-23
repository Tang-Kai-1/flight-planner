package io.codelex.flightplanner;

import io.codelex.flightplanner.flights.domain.Flights;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlightPlannerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlightPlannerApplication.class, args);
    }
}
