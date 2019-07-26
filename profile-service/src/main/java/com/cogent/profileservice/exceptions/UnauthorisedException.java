package com.cogent.profileservice.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UnauthorisedException extends RuntimeException {
    private ErrorResponse errorResponse;

    public UnauthorisedException(String message, String developerMessage) {
        super(message);
        errorResponse = new ErrorResponse(message, developerMessage, HttpStatus.CONFLICT, HttpStatus.CONFLICT.value());
    }
}

