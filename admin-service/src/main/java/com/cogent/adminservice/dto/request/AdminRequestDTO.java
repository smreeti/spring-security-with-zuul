package com.cogent.adminservice.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "This is admin request class")
public class AdminRequestDTO implements Serializable {

    @ApiModelProperty(value = "This is the id of admin")
    private Long id;

    @ApiModelProperty(value = "This is the username of admin")
    private String username;

    @ApiModelProperty(value = "This is full name of admin")
    private String fullName;

    @ApiModelProperty(value = "This is the email address of admin")
    private String emailAddress;

    @ApiModelProperty(value = "This is the status of admin")
    private Character status;

    @ApiModelProperty(value = "This represents the profile and its respective user menus" +
            " that this admin has access to")
    private Long profileId;

    @ApiModelProperty(value = "This checks if email has been sent")
    private Character emailSent;

    @ApiModelProperty(value = "This is the password of admin")
    private String password;
}






