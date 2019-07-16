package com.cogent.authservice.exceptionHandler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {

    private HttpStatus status;
    private String errorMsg;
    private String developerMsg;
}



