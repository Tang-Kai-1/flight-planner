package io.codelex.flightplanner.flights.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Same departure and destination point")
public class SameFromToException extends RuntimeException{
    public SameFromToException(String errorMessage){
        super(errorMessage);
    }
}
