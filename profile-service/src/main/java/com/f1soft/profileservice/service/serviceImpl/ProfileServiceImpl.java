package com.f1soft.profileservice.service.serviceImpl;

import com.f1soft.profileservice.ProfileRequestDTO;
import com.f1soft.profileservice.entities.Profile;
import com.f1soft.profileservice.service.ProfileService;
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
