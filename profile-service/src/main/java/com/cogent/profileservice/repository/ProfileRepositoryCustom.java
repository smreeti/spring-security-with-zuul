package com.cogent.profileservice.repository;

import com.cogent.profileservice.dto.requestDTO.ProfileDTO;
import com.cogent.profileservice.dto.responseDTO.ProfileDetailResponseDTO;
import com.cogent.profileservice.dto.responseDTO.ProfileMinimalResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * @author smriti on 7/10/19
 */
@Repository
@Qualifier("profileRepositoryCustom")
public interface ProfileRepositoryCustom {

    BigInteger findProfileCountByName(String name);

    BigInteger findProfileCountByIdAndName(Long id, String name);

    Optional<List<ProfileMinimalResponseDTO>> searchProfile(ProfileDTO profileDTO);

    ProfileDetailResponseDTO fetchAllProfileDetails(Long id);

}
