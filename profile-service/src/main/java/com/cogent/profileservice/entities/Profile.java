package com.cogent.profileservice.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author smriti on 7/2/19
 */
@Entity
@Table(name = "profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "active")
    private Character active;

    @Column(name = "profile_description")
    private String profileDescription;

    @Column(name = "profile_name")
    private String profileName;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "sub_department_id")
    private Long subDepartmentId;

}
