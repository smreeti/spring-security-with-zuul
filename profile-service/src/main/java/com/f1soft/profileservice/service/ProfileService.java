package com.f1soft.profileservice.service;

import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.requestDTO.ProfileRequestDTO;
import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;

import java.util.List;

/**
 * @author smriti on 7/2/19
 */
public interface ProfileService {

    void createProfile(ProfileRequestDTO profileRequestDTO);

    List<ProfileMinimalResponseDTO> searchProfile(ProfileDTO profileDTO);
}
