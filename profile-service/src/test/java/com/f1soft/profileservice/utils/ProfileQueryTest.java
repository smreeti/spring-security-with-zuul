package com.f1soft.profileservice.utils;

import com.f1soft.profileservice.service.serviceImpl.ProfileServiceImpl;
import com.f1soft.profileservice.utility.QueryCreator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

/**
 * @author smriti on 7/10/19
 */
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
public class ProfileQueryTest {

    @PersistenceContext
    private EntityManager testEntityManager;

    @InjectMocks
    private ProfileServiceImpl profileService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();




    @Test
    public void Should_ThrowException_When_ProfileIsEmpty() {

        List results =  testEntityManager.createNativeQuery
                (QueryCreator.createQueryToSearchProfile.apply(null)).getResultList();

//        System.out.println(results);


        given(profileService.getQueryToSearchProfile(null)).willReturn(new ArrayList<>());

        profileService.searchProfile(null);
    }
}
