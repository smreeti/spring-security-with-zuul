package com.cogent.profileservice.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorResponse implements Serializable {

    private String errorMsg;

    private String developerMsg;

    private HttpStatus responseStatus;

    private int responseCode;
}



