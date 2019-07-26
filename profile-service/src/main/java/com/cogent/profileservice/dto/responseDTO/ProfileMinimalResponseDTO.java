package com.cogent.profileservice.dto.responseDTO;

import lombok.*;

import java.io.Serializable;

/**
 * @author smriti on 7/10/19
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProfileMinimalResponseDTO implements Serializable {

    private Long id;

    private String name;

    private Character status;

    private Long departmentId;

    private Long subDepartmentId;
}
