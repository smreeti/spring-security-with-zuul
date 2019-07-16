package com.cogent.profileservice.service;

import com.cogent.profileservice.ProfileRequestDTO;
import com.cogent.profileservice.entities.Profile;

/**
 * @author smriti on 7/2/19
 */
public interface ProfileService {

    Profile saveProfile(ProfileRequestDTO profileRequestDTO);
}
