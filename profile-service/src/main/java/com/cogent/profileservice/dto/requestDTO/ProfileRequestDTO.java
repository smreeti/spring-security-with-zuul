package com.cogent.profileservice.dto.requestDTO;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author smriti on 7/4/19
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequestDTO implements Serializable {

    private ProfileDTO profileDTO;

    private List<ProfileMenuRequestDTO> profileMenuRequestDTO;
}
