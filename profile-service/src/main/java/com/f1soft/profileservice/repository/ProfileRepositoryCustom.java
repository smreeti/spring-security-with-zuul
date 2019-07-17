package com.f1soft.profileservice.repository;

import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.responseDTO.ProfileDetailResponseDTO;
import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author smriti on 7/10/19
 */
@Repository
@Qualifier("customRepo")
public interface ProfileRepositoryCustom {

    Optional<List<ProfileMinimalResponseDTO>> searchProfile(ProfileDTO profileDTO);

    ProfileDetailResponseDTO fetchAllProfileDetails(Long id);
}
