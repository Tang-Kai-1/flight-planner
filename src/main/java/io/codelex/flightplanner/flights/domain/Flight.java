package io.codelex.flightplanner.flights.domain;

import io.codelex.flightplanner.flights.response.NullException;
import io.codelex.flightplanner.flights.response.SameFromToException;
import io.codelex.flightplanner.flights.response.TimeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Flight {
    private int id;
    private Airport from;
    private Airport to;
    private String carrier;
    private String departureTime;
    private String arrivalTime;

    public Flight(int id, FlightRequest flightRequest) {
        this.id = id;
        this.from = flightRequest.getFrom();
        this.to = flightRequest.getTo();
        this.carrier = flightRequest.getCarrier();
        this.departureTime = flightRequest.getDepartureTime();//new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(flightRequest.getDepartureTime());
        this.arrivalTime = flightRequest.getArrivalTime();//new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(flightRequest.getArrivalTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean checkValidity() throws NullException, TimeException, ParseException, SameFromToException {

        if (from == null || to == null || carrier == null || carrier.equals("") || arrivalTime == null ||
                departureTime == null || from.seeIfNull() || to.seeIfNull()) {
            throw new NullException("Cannot contain null or empty values!");
        }
        if (from.getAirport().trim().equalsIgnoreCase(to.getAirport().trim())) {
            throw new SameFromToException("Destination and departure point match");
        }

        Date arrival = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(arrivalTime);
        Date departure = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(departureTime);
        if (departure.after(arrival) || arrival.compareTo(departure) == 0) {
            throw new TimeException("You cannot get there earlier than you departure. Teleportation also doesn't work!");
        }
        return true;
    }
}
