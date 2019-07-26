package com.cogent.profileservice.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author smriti on 7/6/19
 */
@Entity
@Table(name = "profile_menu")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProfileMenu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "user_menu_id")
    private Long userMenuId;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "status")
    private Character status;
}
