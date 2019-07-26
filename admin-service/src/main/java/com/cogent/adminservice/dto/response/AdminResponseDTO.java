package com.cogent.adminservice.dto.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponseDTO  implements Serializable{

    private String emailAddress;

    private Long id;

    private String password;

    private Character status;

    private Integer loginAttempt;
}
