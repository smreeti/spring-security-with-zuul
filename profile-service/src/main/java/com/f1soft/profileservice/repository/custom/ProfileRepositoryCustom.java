package com.f1soft.profileservice.repository.custom;

import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;

import java.util.List;

/**
 * @author smriti on 7/10/19
 */

public interface ProfileRepositoryCustom {

    List<ProfileMinimalResponseDTO> searchProfile(ProfileDTO profile);
}
