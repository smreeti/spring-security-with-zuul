package com.cogent.profileservice;

import lombok.*;

import java.io.Serializable;

/**
 * @author smriti on 7/4/19
 */

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequestDTO implements Serializable {

    private String profileName;

    private String profileDescription;

    private Character active;

    private Long departmentId;

    private Long subDepartmentId;
}
