package com.f1soft.profileservice.service;

import com.f1soft.profileservice.requestDTO.ProfileRequestDTO;

/**
 * @author smriti on 7/2/19
 */
public interface ProfileService {

    void createProfile(ProfileRequestDTO profileRequestDTO);

//    List<ProfileMinimalResponseDTO> searchProfile(ProfileDTO profileDTO);
}
