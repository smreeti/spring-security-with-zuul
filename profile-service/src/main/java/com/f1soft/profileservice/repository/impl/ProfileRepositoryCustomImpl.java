package com.f1soft.profileservice.repository.impl;

import com.f1soft.profileservice.repository.ProfileRepositoryCustom;
import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;
import com.f1soft.profileservice.utility.QueryCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

/**
 * @author smriti on 7/10/19
 */
@Service
@Transactional
public class ProfileRepositoryCustomImpl implements ProfileRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProfileMinimalResponseDTO> searchProfile(ProfileDTO profileDTO) {

        List<Object[]> queryResult = getQueryToSearchProfile(profileDTO);
        return null;
    }

    public List<Object[]> getQueryToSearchProfile(ProfileDTO profileDTO) {
        Query query = entityManager.createNativeQuery(QueryCreator.createQueryToSearchProfile.apply(profileDTO));

        return query.getResultList();
    }
}
