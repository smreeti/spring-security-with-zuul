package com.f1soft.profileservice.service;

import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.requestDTO.ProfileRequestDTO;
import com.f1soft.profileservice.responseDTO.ProfileDetailResponseDTO;
import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author smriti on 7/2/19
 */
public interface ProfileService {

    void createProfile(ProfileRequestDTO requestDTO);

    void updateProfile(ProfileRequestDTO requestDTO);

    void deleteProfile(Long id);

    List<ProfileMinimalResponseDTO> searchProfile(ProfileDTO profileDTO);

    ProfileDetailResponseDTO fetchAllProfileDetails(Long id);

}
