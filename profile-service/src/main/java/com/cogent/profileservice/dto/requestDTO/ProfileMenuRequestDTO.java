package com.cogent.profileservice.dto.requestDTO;

import lombok.*;

import java.io.Serializable;

/**
 * @author smriti on 7/8/19
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileMenuRequestDTO implements Serializable{

    private Long profileMenuId;

    private Long userMenuId;

    private Long roleId;

    private Character status;
}
