package com.cogent.adminservice.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "admin")
@Entity
@Getter
@Setter
public class Admin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "password")
    private String password;

    @Column(name = "email_address", length = 200)
    private String emailAddress;

    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "status")
    private Character status;

    @Column(name = "login_attempt")
    private Integer loginAttempt;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();


    @Column(name = "email_sent")
    private Character emailSent;
}



