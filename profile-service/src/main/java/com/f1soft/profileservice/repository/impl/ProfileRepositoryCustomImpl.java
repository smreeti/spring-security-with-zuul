package com.f1soft.profileservice.repository.impl;

import com.f1soft.profileservice.entities.Profile;
import com.f1soft.profileservice.repository.ProfileRepository;
import com.f1soft.profileservice.repository.custom.ProfileRepositoryCustom;
import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;
import com.f1soft.profileservice.utility.QueryCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
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
//@Qualifier("repo2")
//@Primary
public class ProfileRepositoryCustomImpl implements ProfileRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProfileMinimalResponseDTO> searchProfile(ProfileDTO profile) {

        Query query = entityManager.createNativeQuery(QueryCreator.createQueryToSearchProfile.apply(null));

        List<Objects[]> results = query.getResultList();


        System.out.println(results);
//        entityManager.refresh(profile);
        return null;
    }


}
