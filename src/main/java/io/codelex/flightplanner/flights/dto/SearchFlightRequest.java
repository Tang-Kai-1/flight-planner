package io.codelex.flightplanner.flights.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchFlightRequest {

    private String from;
    private String to;
    private LocalDate departureDate;

    public SearchFlightRequest(String from, String to, String departureDate) {
        this.from = from;
        this.to = to;
        this.departureDate = LocalDate.parse(departureDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDate getDepartureTime() {
        return departureDate;
    }

    public void setDepartureTime(LocalDate departureTime) {
        this.departureDate = departureTime;
    }

    public boolean checkValidity() {
        if (from == null || to == null || departureDate == null || from.equals("") || to.equals("") || departureDate.equals("")) {
            return false;
        }
        return true;
    }
}
