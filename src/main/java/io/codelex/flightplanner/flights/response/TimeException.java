package io.codelex.flightplanner.flights.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Teleportation or time travel impossible currently")
public class TimeException extends RuntimeException{
    public TimeException(String errorMessage){
        super(errorMessage);
    }
}
