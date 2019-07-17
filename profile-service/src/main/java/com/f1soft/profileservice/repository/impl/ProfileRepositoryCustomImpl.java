package com.f1soft.profileservice.repository.impl;

import com.f1soft.profileservice.repository.ProfileRepositoryCustom;
import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.responseDTO.ProfileDetailResponseDTO;
import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;
import com.f1soft.profileservice.utility.ProfileUtils;
import com.f1soft.profileservice.utility.QueryCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.f1soft.profileservice.utility.ProfileUtils.convertObjectToProfileResponseDTO;

/**
 * @author smriti on 7/10/19
 */
@Service
public class ProfileRepositoryCustomImpl implements ProfileRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<List<ProfileMinimalResponseDTO>> searchProfile(ProfileDTO profileDTO) {

        Query query = entityManager.createNativeQuery(QueryCreator.createQueryToSearchProfile.apply(profileDTO));

        List<Object[]> results = query.getResultList();

        return Optional.of(results.stream().map(convertObjectToProfileResponseDTO)
                .collect(Collectors.toList()));
    }

    @Override
    public ProfileDetailResponseDTO fetchAllProfileDetails(Long id) {

        Query query = entityManager.createNativeQuery(QueryCreator.createQueryToFetchAllProfileDetails.apply(id));

        List<Object[]> results = query.getResultList();

        return ProfileUtils.convertObjectToProfileDetailResponse.apply(results);
    }
}
