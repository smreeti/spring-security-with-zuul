package com.f1soft.profileservice.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author smriti on 7/15/19
 */
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileMenuResponseDTO implements Serializable {
    private Long profileMenuId;

    private Long userMenuId;

    private Long roleId;

    private Character status;
}
