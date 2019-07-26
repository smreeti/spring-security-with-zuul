package com.cogent.loginservice.responseDTO;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponseDTO implements Serializable {

    private String emailAddress;

    private Long id;

    private String password;

    private Integer loginAttempt;

    private Character status;

}
