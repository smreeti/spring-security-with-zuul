package com.f1soft.profileservice.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author smriti on 7/8/19
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    /*Y- ENABLED
    * N- DISABLED
    */

    @Column(name = "status")
    private Character status;

    @Column(name = "action_key")
    private String actionKey;

}
