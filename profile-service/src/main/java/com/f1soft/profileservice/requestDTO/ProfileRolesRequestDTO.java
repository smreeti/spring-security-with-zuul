package com.f1soft.profileservice.requestDTO;

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
public class ProfileRolesRequestDTO implements Serializable{

    private Long userMenuId;

    private Long roleId;
}
