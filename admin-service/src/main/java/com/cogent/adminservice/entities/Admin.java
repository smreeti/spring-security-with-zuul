package com.cogent.adminservice.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "admin")
@Entity
@Getter
@Setter
public class Admin extends BaseEntity<Long> {

    @Column(name = "username")
    private String username;

    @Column(name = "email_address", length = 200)
    private String emailAddress;

    @Column(name = "password")
    private String password;

    @Column(name = "email_sent")
    private Character emailSent;

    @Column(name = "status")
    private Character status;

    @Column(name = "login_attempt")
    private Integer loginAttempt;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public Admin(String username, String password, String emailAddress,
                 Character status, Integer loginAttempt, List<String> roles) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.status = status;
        this.loginAttempt = loginAttempt;
        this.roles = roles;
    }

    public Admin() {

    }
}
