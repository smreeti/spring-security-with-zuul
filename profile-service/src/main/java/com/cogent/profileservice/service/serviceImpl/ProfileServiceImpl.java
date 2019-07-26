package com.cogent.profileservice.service.serviceImpl;

import com.cogent.profileservice.ProfileRequestDTO;
import com.cogent.profileservice.entities.Profile;
import com.cogent.profileservice.service.ProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author smriti on 7/2/19
 */
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    @Override
    public Profile saveProfile(ProfileRequestDTO profileRequestDTO) {
        return new Profile();
    }
}
