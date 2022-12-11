package io.codelex.flightplanner.flights.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Null or empty values.")
public class NullException extends RuntimeException{
    public NullException(String errorMessage){
        super(errorMessage);
    }
}
