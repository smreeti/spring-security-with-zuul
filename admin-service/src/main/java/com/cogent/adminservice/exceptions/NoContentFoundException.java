package com.cogent.adminservice.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NoContentFoundException extends RuntimeException {
    private ErrorResponse errorResponse;

    public NoContentFoundException(String message, String developerMessage) {
        super(message);
        errorResponse = new ErrorResponse();

        errorResponse.setDeveloperMsg(developerMessage);
        errorResponse.setErrorMsg(message);
        errorResponse.setResponseCode(HttpStatus.NO_CONTENT.value());
        errorResponse.setResponseStatus(HttpStatus.NO_CONTENT);
    }
}
