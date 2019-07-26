package com.cogent.profileservice.repository.impl;

import com.cogent.profileservice.repository.ProfileRepositoryCustom;
import com.cogent.profileservice.dto.requestDTO.ProfileDTO;
import com.cogent.profileservice.dto.responseDTO.ProfileDetailResponseDTO;
import com.cogent.profileservice.dto.responseDTO.ProfileMinimalResponseDTO;
import com.cogent.profileservice.utility.ProfileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.cogent.profileservice.utility.ProfileUtils.convertObjectToProfileResponseDTO;
import static com.cogent.profileservice.utility.QueryCreator.*;

/**
 * @author smriti on 7/10/19
 */
@Service
@Transactional
public class ProfileRepositoryCustomImpl implements ProfileRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<List<ProfileMinimalResponseDTO>> searchProfile(ProfileDTO profileDTO) {

        Query query = entityManager.createNativeQuery(createQueryToSearchProfile.apply(profileDTO));

        List<Object[]> results = query.getResultList();

        return Optional.of(results.stream().map(convertObjectToProfileResponseDTO)
                .collect(Collectors.toList()));
    }

    @Override
    public ProfileDetailResponseDTO fetchAllProfileDetails(Long id) {

        Query query = entityManager.createNativeQuery(fetchAllProfileDetails.apply(id));

        List<Object[]> results = query.getResultList();

        return ProfileUtils.convertObjectToProfileDetailResponseDTO.apply(results);
    }

    @Override
    public BigInteger findProfileCountByName(String name) {
        Query query = entityManager.createNativeQuery(createQueryToFindProfileCountByName.get())
                .setParameter("name", name);

        return (BigInteger) query.getSingleResult();
    }

    @Override
    public BigInteger findProfileCountByIdAndName(Long id, String name) {
        Query query = entityManager.createNativeQuery(createQueryToFindProfileCountByIdAndName.get())
                .setParameter("id", id)
                .setParameter("name", name);

        return ((BigInteger) query.getSingleResult());
    }
}
