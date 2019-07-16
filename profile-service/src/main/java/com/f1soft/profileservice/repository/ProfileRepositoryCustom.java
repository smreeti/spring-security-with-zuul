package com.f1soft.profileservice.repository;

import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.responseDTO.ProfileDetailResponseDTO;
import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author smriti on 7/10/19
 */
@Repository
public interface ProfileRepositoryCustom {

    List<ProfileMinimalResponseDTO> searchProfile(ProfileDTO profileDTO);

    ProfileDetailResponseDTO fetchAllProfileDetails (Long id);

}
