package io.codelex.flightplanner.flights.dto;

import io.codelex.flightplanner.flights.domain.Airport;
import io.codelex.flightplanner.flights.response.NullException;
import io.codelex.flightplanner.flights.response.SameFromToException;
import io.codelex.flightplanner.flights.response.TimeException;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FlightRequest {
    private Airport from;
    private Airport to;
    private String carrier;
    private String departureTime;
    private String arrivalTime;

    public FlightRequest(Airport from, Airport to, String carrier, String departureTime, String arrivalTime) {
        this.from = from;
        this.to = to;
        this.carrier = carrier;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public boolean checkValidity() {
        System.out.println("Checking validity of flight request");
        System.out.println("Departure:");
        System.out.println(departureTime);
        System.out.println("Arrival:");
        System.out.println(arrivalTime);
        if (from == null || to == null || carrier == null || carrier.equals("") || arrivalTime == null ||
                departureTime == null || from.seeIfNull() || to.seeIfNull()) {
            throw new NullException("Cannot contain null or empty values!");
        }
        if (from.getAirport().trim().equalsIgnoreCase(to.getAirport().trim())) {
            throw new SameFromToException("Destination and departure point match");
        }

        LocalDateTime arrival = LocalDateTime.parse(arrivalTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));;
        LocalDateTime departure = LocalDateTime.parse(departureTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));;
        if (departure.isAfter(arrival) || arrival.compareTo(departure) == 0) {
            throw new TimeException("You cannot get there earlier than you departure. Teleportation also doesn't work!");
        }
        return true;
    }
}
