//package com.f1soft.profileservice.utils;
//
//import com.f1soft.profileservice.repository.impl.ProfileRepositoryCustomImpl;
//import com.f1soft.profileservice.service.serviceImpl.ProfileServiceImpl;
//import com.f1soft.profileservice.utility.QueryCreator;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//
//import static org.mockito.Mockito.mock;
//
///**
// * @author smriti on 7/10/19
// */
//@RunWith(SpringRunner.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DataJpaTest
//public class ProfileQueryTest {
//
//
//    @InjectMocks
//    private ProfileServiceImpl profileService;
//
//    @Autowired
//    private EntityManager testEntityManager;
//
//    @Autowired
//    private ProfileRepositoryCustomImpl profileRepository;
//
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//
//    @Test
//    public void Should_ThrowException_When_ProfileIsEmpty() {
//
//        List results = testEntityManager.createNativeQuery
//                (QueryCreator.createQueryToSearchProfile.apply(null)).getResultList();
//
//        System.out.println(results);
//
////        profileRepository.refresh(new ProfileDTO());
//
////        profileService.getQueryToSearchProfile(new ProfileDTO());
//
//
////
////        given(profileService.getQueryToSearchProfile(null)).willReturn(new ArrayList<>());
//
////        profileRepository.refresh(new ProfileDTO());
//    }
//}
