package com.f1soft.profileservice.repository;

import com.f1soft.profileservice.constants.ErrorMessageConstants;
import com.f1soft.profileservice.exceptions.NoContentFoundException;
import com.f1soft.profileservice.repository.impl.ProfileRepositoryCustomImpl;
import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;
import com.f1soft.profileservice.service.serviceImpl.ProfileServiceImpl;
import com.f1soft.profileservice.utility.ProfileUtils;
import com.f1soft.profileservice.utility.QueryCreator;
import com.f1soft.profileservice.utils.ProfileRequestUtils;
import com.f1soft.profileservice.utils.ProfileResponseUtils;
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
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

import static com.f1soft.profileservice.utils.ProfileRequestUtils.*;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.util.AssertionErrors.assertEquals;
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
    public void searchProfile() {

        Query query = entityManager.createNativeQuery(QueryCreator.createQueryToSearchProfile.apply(null));

        List<Object[]> list = query.getResultList();

        List<ProfileMinimalResponseDTO> responseDTOS = list.stream().map(
                ProfileUtils.convertObjectToProfileResponseDTO)
                .collect(Collectors.toList());

        given(profileRepositoryCustom.searchProfile(null)).willReturn(responseDTOS);
//
//        Assertions.assertThat()


        thrown.expect(NoContentFoundException.class);

        profileRepositoryCustom.searchProfile(null);


//        profileRepository.refresh(new ProfileDTO());

//        profileService.getQueryToSearchProfile(new ProfileDTO());


//
//        given(profileService.getQueryToSearchProfile(null)).willReturn(new ArrayList<>());

//        profileRepository.refresh(new ProfileDTO());
    }
}
