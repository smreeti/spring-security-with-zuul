package com.f1soft.profileservice.repository;

import com.f1soft.profileservice.exceptions.NoContentFoundException;
import com.f1soft.profileservice.repository.impl.ProfileRepositoryCustomImpl;
import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;
import com.f1soft.profileservice.utility.QueryCreator;
import com.f1soft.profileservice.utils.ProfileRequestUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

import static com.f1soft.profileservice.utils.ProfileRequestUtils.*;
import static com.f1soft.profileservice.utils.ProfileResponseUtils.getProfileMinimalResponseList;
import static junit.framework.TestCase.assertFalse;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author smriti on 7/11/19
 */

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ProfileRepositoryCustomTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    ProfileRepositoryCustomImpl profileRepositoryCustom;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void searchProfile() {
        Should_ThrowException_When_ProfileListIsEmpty();

        Should_ReturnProfileList_When_ProfileListIsNotEmpty();
    }

    @Test
    public void Should_ThrowException_When_ProfileListIsEmpty() {

        Query query = testEntityManager.getEntityManager().createNativeQuery(
                QueryCreator.createQueryToSearchProfile.apply(getProfileDTO()));

        profileRepositoryCustom.searchProfile(getProfileDTO());

        assertThat(query.getResultList()).isEqualTo(Collections.emptyList());

        thrown.expect(NoContentFoundException.class);
    }

    @Test
    public void Should_ReturnProfileList_When_ProfileListIsNotEmpty() {

        Query query = testEntityManager.getEntityManager().createNativeQuery(
                QueryCreator.createQueryToSearchProfile.apply(getProfileDTO()));

        List<ProfileMinimalResponseDTO> expected = profileRepositoryCustom.searchProfile(getProfileDTO());

        assertFalse(query.getResultList().isEmpty());

        assertFalse(expected.isEmpty());

        assertThat(expected).hasSize(getProfileMinimalResponseList().size());
    }
}
