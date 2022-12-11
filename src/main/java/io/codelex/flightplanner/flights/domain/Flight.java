package io.codelex.flightplanner.flights.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import io.codelex.flightplanner.flights.dto.FlightRequest;
import io.codelex.flightplanner.flights.response.NullException;
import io.codelex.flightplanner.flights.response.SameFromToException;
import io.codelex.flightplanner.flights.response.TimeException;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Flight {

    @Transient
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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
    @Column(name="DEPARTURE_TIME", nullable=false, unique=true, columnDefinition = "TIMESTAMP")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime departureTime;
    @Column(name="ARRIVAL_TIME", nullable=false, unique=true, columnDefinition = "TIMESTAMP")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime arrivalTime;

    public Flight(int id, FlightRequest flightRequest) {
        this.id = id;
        this.from = flightRequest.getFrom();
        this.to = flightRequest.getTo();
        this.carrier = flightRequest.getCarrier();
        this.departureTime = LocalDateTime.parse(flightRequest.getDepartureTime(),formatter);
        this.arrivalTime = LocalDateTime.parse(flightRequest.getArrivalTime(),formatter);
    }

    public Flight(FlightRequest flightRequest) {
        this.from = flightRequest.getFrom();
        this.to = flightRequest.getTo();
        this.carrier = flightRequest.getCarrier();
        this.departureTime = LocalDateTime.parse(flightRequest.getDepartureTime(),formatter);
        this.arrivalTime = LocalDateTime.parse(flightRequest.getArrivalTime(),formatter);
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

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
