package com.f1soft.departmentservice.exception;

import com.f1soft.departmentservice.error.ErrorResponse;
import lombok.Data;
import lombok.Getter;
/**
 * @author Sauravi
 */

@Data
public class DataAlreadyExistsException extends RuntimeException{
    private ErrorResponse errorResponse;

    public DataAlreadyExistsException(String message) {
       super(message);
        errorResponse=ErrorResponse.builder().errorMsg(message).build();

    }
}
