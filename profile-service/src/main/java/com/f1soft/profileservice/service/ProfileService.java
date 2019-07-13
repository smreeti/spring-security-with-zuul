package com.f1soft.profileservice.service;

import com.f1soft.profileservice.requestDTO.ProfileRequestDTO;

/**
 * @author smriti on 7/2/19
 */
public interface ProfileService {

    void createProfile(ProfileRequestDTO profileRequestDTO);

    void updateProfile(ProfileRequestDTO requestDTO);
}
