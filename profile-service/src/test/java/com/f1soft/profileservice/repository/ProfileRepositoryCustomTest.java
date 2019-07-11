package com.f1soft.profileservice.repository;

import com.f1soft.profileservice.exceptions.NoContentFoundException;
import com.f1soft.profileservice.repository.impl.ProfileRepositoryCustomImpl;
import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.service.serviceImpl.ProfileServiceImpl;
import com.f1soft.profileservice.utility.QueryCreator;
import com.f1soft.profileservice.utils.ProfileRequestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.f1soft.profileservice.utils.ProfileRequestUtils.*;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * @author smriti on 7/11/19
 */

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ProfileRepositoryCustomTest {

    @Autowired
     EntityManager entityManager;

    @Autowired
     ProfileRepositoryCustomImpl profileRepositoryCustom;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void Should_ThrowException_When_ProfileIsEmpty() {

        ProfileDTO profileDTO = getProfileDTO();

//        assertTrue(profileRepositoryCustom.);

        given(profileRepositoryCustom.getQueryToSearchProfile(profileDTO)).willReturn(new AbstractList<Object[]>() {
            @Override
            public Object[] get(int index) {
                return new Object[0];
            }

            @Override
            public int size() {
                return 0;
            }
        });
        thrown.expect(NoContentFoundException.class);

        profileRepositoryCustom.searchProfile(profileDTO);

        List results = entityManager.createNativeQuery
                (QueryCreator.createQueryToSearchProfile.apply(null)).getResultList();

        System.out.println(results);

//        profileRepository.refresh(new ProfileDTO());

//        profileService.getQueryToSearchProfile(new ProfileDTO());


//
//        given(profileService.getQueryToSearchProfile(null)).willReturn(new ArrayList<>());

//        profileRepository.refresh(new ProfileDTO());
    }
}
