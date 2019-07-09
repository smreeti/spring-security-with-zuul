package com.f1soft.profileservice.requestDTO;

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
public class ProfileGeneralInfoRequestDTO implements Serializable {

    private String profileName;

    private String profileDescription;

    private Character status;

    private Long departmentId;

    private Long subDepartmentId;
}
