package com.cogent.adminservice.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel(value = "This is admin request class")
public class AdminRequestDTO implements Serializable {

    private Long id;

    @ApiModelProperty(value = "This is the username of admin")
    private String username;

    private String emailAddress;

    private Character emailSent;

    private String password;

    private Integer loginAttempt;

    private Character status;
}
