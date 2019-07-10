package com.f1soft.departmentservice.exception;

import com.f1soft.departmentservice.error.ErrorResponse;
import lombok.Data;
/**
 * @author Sauravi
 */

@Data
public class NoChangeFoundException extends RuntimeException{
    private ErrorResponse errorResponse;

    public NoChangeFoundException(String message) {
       super(message);
        errorResponse=ErrorResponse.builder().errorMsg(message).build();

    }
}
