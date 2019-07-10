//package com.f1soft.profileservice.repository.impl;
//
//import com.f1soft.profileservice.entities.Profile;
//import com.f1soft.profileservice.repository.ProfileRepository;
//import com.f1soft.profileservice.requestDTO.ProfileDTO;
//import com.f1soft.profileservice.utility.QueryCreator;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//
///**
// * @author smriti on 7/10/19
// */
//
//public class ProfileRepositoryImpl implements ProfileRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public Profile findByName(String name) {
//        return null;
//    }
//
//    @Override
//    public Profile getQueryToSearchProfile(ProfileDTO profileDTO) {
//        return null;
//    }
//
////    @Override
////    public Query getQueryToSearchProfile(ProfileDTO profileDTO) {
////        return entityManager.createNativeQuery(QueryCreator.createQueryToSearchProfile.apply(profileDTO));
////    }
//
//    @Override
//    public Profile save(Profile profile) {
//        return null;
//    }
//
//
//}
