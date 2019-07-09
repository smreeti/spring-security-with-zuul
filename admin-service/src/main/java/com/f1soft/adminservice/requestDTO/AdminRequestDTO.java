package com.f1soft.adminservice.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel(value = "This is admin request class")
public class AdminRequestDTO implements Serializable {

    @ApiModelProperty(value = "This is the id of admin")
    private Long id;

    @ApiModelProperty(value = "This is the username of admin")
    private String username;

    @ApiModelProperty(value = "This is the email address of admin")
    private String emailAddress;

    @ApiModelProperty(value = "This checks if email has been sent")
    private Character emailSent;

    @ApiModelProperty(value = "This is the password of admin")
    private String password;

    @ApiModelProperty(value = "This is login attempts that admin has done")
    private Integer loginAttempt;

    @ApiModelProperty(value = "This is the status of admin")
    private Character status;
}
