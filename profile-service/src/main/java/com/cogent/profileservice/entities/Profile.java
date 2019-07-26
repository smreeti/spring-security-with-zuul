package com.cogent.profileservice.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Profile id that is auto-generated")
    private Long id;

    @Column(name = "name")
    @ApiModelProperty(value = "Profile name")
    private String name;

    @Column(name = "description")
    @ApiModelProperty(value = "Profile description")
    private String description;

    @Column(name = "status")
    @ApiModelProperty(value = "Profile status: Y-Active, D-Deleted")
    private Character status;

    @Column(name = "department_id")
    @ApiModelProperty(value = "Department id assigned to profile")
    private Long departmentId;

    @Column(name = "sub_department_id")
    @ApiModelProperty(value = "Sub-dept id assigned to profile")
    private Long subDepartmentId;

}
