package io.codelex.flightplanner.flights.dto;

import java.util.Date;

public class SearchFlightRequest {

    private String from;
    private String to;
    private String departureDate;

    public SearchFlightRequest(String from, String to, String departureDate) {
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
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

    public String getDepartureTime() {
        return departureDate;
    }

    public void setDepartureTime(String departureTime) {
        this.departureDate = departureTime;
    }

    public boolean checkValidity() {
        if (from == null || to == null || departureDate == null || from.equals("") || to.equals("") || departureDate.equals("")) {
            return false;
        }
        return true;
    }
}
