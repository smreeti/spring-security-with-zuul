package com.cogent.profileservice.dto.responseDTO;

import lombok.*;

import java.io.Serializable;

/**
 * @author smriti on 7/15/19
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileMenuResponseDTO implements Serializable {
    private Long profileMenuId;

    private Long userMenuId;

    private Long roleId;
}
