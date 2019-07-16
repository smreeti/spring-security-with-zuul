package com.cogent.exceptionHandler;


import lombok.Data;

/**
 * @author Sauravi
 */

@Data
public class DataAlreadyExistsException extends RuntimeException {
    private ErrorResponse errorResponse;

    public DataAlreadyExistsException(String message) {
        super(message);
        errorResponse = ErrorResponse.builder().errorMsg(message).build();

    }
}
