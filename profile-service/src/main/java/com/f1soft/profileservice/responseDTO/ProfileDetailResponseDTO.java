package com.f1soft.profileservice.responseDTO;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author smriti on 7/15/19
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDetailResponseDTO implements Serializable {

    private ProfileResponseDTO profileResponseDTO;

    private List<ProfileMenuResponseDTO> profileMenuResponseDTOS;
}
