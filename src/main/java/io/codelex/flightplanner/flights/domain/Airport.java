package io.codelex.flightplanner.flights.domain;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Airport {
    @Column(name="COUNTRY", nullable=false, unique=true)
    private String country;
    @Column(name="CITY", nullable=false, unique=true)
    private String city;
    @Column(name="AIRPORT", nullable=false, unique=true)
    @Id
    private String airport;

    public Airport(String country, String city, String airport) {
        this.country = country;
        this.city = city;
        this.airport = airport;
    }

    public Airport() {

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public boolean seeIfNull() {
        if (country == null || city == null || airport == null || country.equals("") || city.equals("") || airport.equals("")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport1 = (Airport) o;
        return country.equalsIgnoreCase(airport1.country) && city.equalsIgnoreCase(airport1.city) && airport.equalsIgnoreCase(airport1.airport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, airport);
    }
}
