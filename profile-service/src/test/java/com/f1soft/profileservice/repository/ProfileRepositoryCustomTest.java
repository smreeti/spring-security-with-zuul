package com.f1soft.profileservice.repository;

import com.f1soft.profileservice.exceptions.NoContentFoundException;
import com.f1soft.profileservice.repository.impl.ProfileRepositoryCustomImpl;
import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;
import com.f1soft.profileservice.utility.QueryCreator;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    TestEntityManager entityManager;

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

        Query query = entityManager.getEntityManager().createNativeQuery(
                QueryCreator.createQueryToSearchProfile.apply(null));

        profileRepositoryCustom.searchProfile(null);

        assertThat(query.getResultList()).isEqualTo(Collections.emptyList());

        thrown.expect(NoContentFoundException.class);
    }

    @Test
    public void Should_ReturnProfileList_When_ProfileListIsNotEmpty() {

        Query query = entityManager.getEntityManager().createNativeQuery(
                QueryCreator.createQueryToSearchProfile.apply(null));

        List<ProfileMinimalResponseDTO> expected = profileRepositoryCustom.searchProfile(null);

        assertFalse(query.getResultList().isEmpty());

        assertFalse(expected.isEmpty());

        assertThat(expected).hasSize(getProfileList().size());
    }

    public List<ProfileMinimalResponseDTO> getProfileList() {
        return Arrays.asList(
                new ProfileMinimalResponseDTO(1L, "F1soft", 'Y', 1L, 2L),
                new ProfileMinimalResponseDTO(2L, "Cogent", 'Y', 1L, 3L));
    }
}
