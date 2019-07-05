package com.f1soft.profileservice.service;

import com.f1soft.profileservice.ProfileRequestDTO;
import com.f1soft.profileservice.entities.Profile;

/**
 * @author smriti on 7/2/19
 */
public interface ProfileService {

    Profile saveProfile(ProfileRequestDTO profileRequestDTO);
}
