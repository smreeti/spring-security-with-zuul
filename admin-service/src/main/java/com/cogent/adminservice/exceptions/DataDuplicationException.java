package com.cogent.adminservice.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author smriti on 7/6/19
 */
@Getter
@Setter
public class DataDuplicationException extends RuntimeException {
    private ErrorResponse errorResponse;

    public DataDuplicationException(String message, String developerMessage) {
        super(message);
        errorResponse = new ErrorResponse(message, developerMessage, HttpStatus.CONFLICT, HttpStatus.CONFLICT.value());
    }
}
