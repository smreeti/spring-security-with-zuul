package com.f1soft.profileservice.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author smriti on 7/2/19
 */
@Entity
@Table(name = "profile")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Character status;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "sub_department_id")
    private Long subDepartmentId;

}
