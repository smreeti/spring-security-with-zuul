package com.cogent.profileservice.dto.requestDTO;

import lombok.*;

import java.io.Serializable;

/**
 * @author smriti on 7/8/19
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private Character status;

    private Long departmentId;

    private Long subDepartmentId;

}
