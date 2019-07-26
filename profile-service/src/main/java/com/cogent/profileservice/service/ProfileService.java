package com.cogent.profileservice.service;

import com.cogent.profileservice.dto.requestDTO.ProfileDTO;
import com.cogent.profileservice.dto.requestDTO.ProfileRequestDTO;
import com.cogent.profileservice.dto.responseDTO.ProfileDetailResponseDTO;
import com.cogent.profileservice.dto.responseDTO.ProfileMinimalResponseDTO;

import java.util.List;

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
