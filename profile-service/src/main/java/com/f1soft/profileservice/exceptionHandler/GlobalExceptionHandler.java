package com.f1soft.profileservice.exceptionHandler;

import com.f1soft.profileservice.exceptions.DataDuplicationException;
import com.f1soft.profileservice.exceptions.ErrorResponse;
import com.f1soft.profileservice.exceptions.NoContentFoundException;
import com.f1soft.profileservice.exceptions.UnauthorisedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UnauthorisedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorisedException e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getErrorResponse(), e.getErrorResponse().getResponseStatus());
    }

    @ExceptionHandler(value = NoContentFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoContentFoundException(NoContentFoundException e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getErrorResponse(), e.getErrorResponse().getResponseStatus());
    }

    @ExceptionHandler(value = DataDuplicationException.class)
    public ResponseEntity<ErrorResponse> handleDataDuplicationException(DataDuplicationException e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getErrorResponse(), e.getErrorResponse().getResponseStatus());
    }
}
