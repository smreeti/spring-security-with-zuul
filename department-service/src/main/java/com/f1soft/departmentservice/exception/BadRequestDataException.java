package com.f1soft.departmentservice.exception;

import com.f1soft.departmentservice.error.ErrorResponse;
import lombok.Data;

@Data
public class BadRequestDataException extends RuntimeException{
    private ErrorResponse errorResponse;

    public BadRequestDataException(String message) {
       super(message);
        errorResponse=ErrorResponse.builder().errorMsg(message).build();

    }
}
