package com.f1soft.loginservice.exceptionHandler;

import com.f1soft.loginservice.exceptions.ErrorResponse;
import com.f1soft.loginservice.exceptions.NoContentFoundException;
import com.f1soft.loginservice.exceptions.UnauthorisedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ResponseBody
    @ExceptionHandler(value = UnauthorisedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorisedException e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getErrorResponse(), e.getErrorResponse().getResponseStatus());
    }


//    @ExceptionHandler(value = UnauthorisedException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ResponseEntity handleUnauthorizedException(UnauthorisedException e) {
//
//       ErrorResponse errorResponse = new ErrorResponse();
////
////        errorResponse.setDeveloperMsg(developerMessage);
//        errorResponse.setErrorMsg(e.getMessage());
//        errorResponse.setResponseCode(HttpStatus.UNAUTHORIZED.value());
//        errorResponse.setResponseStatus(HttpStatus.UNAUTHORIZED);
//
////        e.printStackTrace();
//        return new ResponseEntity<>(errorResponse, errorResponse.getResponseStatus());
//    }





//    @ExceptionHandler(value = UnauthorisedException.class)
//    public ResponseEntity<ErrorResponse> handleUnauthorizedException(Exception e) {
//
//        ErrorResponse errorResponse = new ErrorResponse();
//        errorResponse.setErrorMsg(e.getMessage());
//
//        errorResponse.setResponseCode(HttpStatus.UNAUTHORIZED.value());
////        errorResponse.setResponseStatus(HttpStatus.UNAUTHORIZED);
//
////        e.printStackTrace();
//        return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED );
//    }

    @ExceptionHandler(value = NoContentFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoContentFoundException(NoContentFoundException e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getErrorResponse(), e.getErrorResponse().getResponseStatus());
    }
}
