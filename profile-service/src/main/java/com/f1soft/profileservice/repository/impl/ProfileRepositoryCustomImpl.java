package com.f1soft.profileservice.repository.impl;

import com.f1soft.profileservice.repository.custom.ProfileRepositoryCustom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author smriti on 7/10/19
 */
@Service
@Transactional
public class ProfileRepositoryCustomImpl implements ProfileRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void test() {

    }

//    @Override
//    public List<ProfileMinimalResponseDTO> searchProfile(ProfileDTO profile) {
//
//        Query query = entityManager.createNativeQuery(QueryCreator.createQueryToSearchProfile.apply(null));
//
//        List<Objects[]> results = query.getResultList();
//
//
//        System.out.println(results);
////        entityManager.refresh(profile);
//        return null;
//    }

//    @Override
//    public void test(){
//        System.out.println("vdsvs");
//
//    }

}
