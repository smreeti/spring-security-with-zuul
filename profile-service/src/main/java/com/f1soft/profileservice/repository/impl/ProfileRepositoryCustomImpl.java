package com.f1soft.profileservice.repository.impl;

import com.f1soft.profileservice.repository.ProfileRepositoryCustom;
import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author smriti on 7/10/19
 */
@Service
@Transactional
public class ProfileRepositoryCustomImpl implements ProfileRepositoryCustom {
   
    @Override
    public List<ProfileMinimalResponseDTO> searchProfile(ProfileDTO profileDTO) {
        return null;
    }
}
