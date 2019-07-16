package com.cogent.authservice.responseDTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author smriti on 6/27/19
 */

@Getter
@Setter
public class AdminResponseDTO implements Serializable{

    private Long id;

    private String username;

    private String emailAddress;

    private String password;

    private Character status;

    private Integer loginAttempt;

    private Date createdDate;

    private Date lastModifiedDate;

    private List<String> roles = new ArrayList<>();
}
