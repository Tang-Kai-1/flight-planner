package io.codelex.flightplanner.flights.domain;

import com.sun.istack.NotNull;
import io.codelex.flightplanner.flights.dto.FlightRequest;
import io.codelex.flightplanner.flights.response.NullException;
import io.codelex.flightplanner.flights.response.SameFromToException;
import io.codelex.flightplanner.flights.response.TimeException;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "AIRPORT_FROM", referencedColumnName = "airport")
    private Airport from;
    @NotNull
    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "AIRPORT_TO", referencedColumnName = "airport")
    private Airport to;
    @Column(name="CARRIER", nullable=false, unique=true)
    private String carrier;
    @Column(name="DEPARTURE_TIME", nullable=false, unique=true)
    private String departureTime;
    @Column(name="ARRIVAL_TIME", nullable=false, unique=true)
    private String arrivalTime;

    public Flight(int id, FlightRequest flightRequest) {
        this.id = id;
        this.from = flightRequest.getFrom();
        this.to = flightRequest.getTo();
        this.carrier = flightRequest.getCarrier();
        this.departureTime = flightRequest.getDepartureTime();
        this.arrivalTime = flightRequest.getArrivalTime();
    }

    public Flight(FlightRequest flightRequest) {
        this.from = flightRequest.getFrom();
        this.to = flightRequest.getTo();
        this.carrier = flightRequest.getCarrier();
        this.departureTime = flightRequest.getDepartureTime();
        this.arrivalTime = flightRequest.getArrivalTime();
    }

    public Flight() {

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

    public boolean checkValidity() throws ParseException {

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
