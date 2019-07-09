package com.f1soft.departmentservice.exception;

import com.f1soft.departmentservice.error.ErrorResponse;
import lombok.Data;

@Data
public class DataNotFoundException extends RuntimeException{
    private ErrorResponse errorResponse;

    public DataNotFoundException(String message) {
       super(message);
        errorResponse=ErrorResponse.builder().errorMsg(message).build();

    }
}
