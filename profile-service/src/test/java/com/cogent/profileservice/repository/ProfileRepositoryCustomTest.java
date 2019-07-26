package com.cogent.profileservice.repository;

import com.cogent.profileservice.repository.impl.ProfileRepositoryCustomImpl;
import com.cogent.profileservice.utility.QueryCreator;
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
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import static com.cogent.profileservice.utils.ProfileRequestUtils.getProfileDTO;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
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
    public void findProfileCountByName() {
        findProfileCountByName_Should_Return_0();

        findProfileCountByName_Should_Return_1();
    }

    @Test
    public void findProfileCountByIdAndName(){
        findProfileCountByIdAndName_Should_Return_0();

        findProfileCountByIdAndName_Should_Return_1();
    }

    @Test
    public void searchProfile() {
        Should_ThrowException_When_ProfileListIsEmpty();

        Should_ReturnProfileList_When_ProfileListIsNotEmpty();
    }

    @Test
    public void Should_ThrowException_When_ProfileListIsEmpty() {

        Query query = testEntityManager.getEntityManager().createNativeQuery(
                QueryCreator.createQueryToSearchProfile.apply(getProfileDTO()));

        assertThat(query.getResultList()).isEqualTo(Collections.emptyList());
    }

    @Test
    public void Should_ReturnProfileList_When_ProfileListIsNotEmpty() {

        Query query = testEntityManager.getEntityManager().createNativeQuery(
                QueryCreator.createQueryToSearchProfile.apply(getProfileDTO()));

        assertFalse(query.getResultList().isEmpty());
    }

    @Test
    public void fetchProfileDetails() {
        Query query = testEntityManager.getEntityManager().createNativeQuery
                (QueryCreator.fetchAllProfileDetails.apply(1L));

        List results = query.getResultList();

        assertNotNull(results);
    }

    @Test
    public void findProfileCountByName_Should_Return_0() {
        BigInteger result = profileRepositoryCustom.findProfileCountByName("F1soft1");
        assertThat(result).isEqualTo(ZERO);
    }

    @Test
    public void findProfileCountByName_Should_Return_1() {
        BigInteger result = profileRepositoryCustom.findProfileCountByName("Cogent");
        assertThat(result).isEqualTo(ONE);
    }

    @Test
    public void findProfileCountByIdAndName_Should_Return_0() {
        BigInteger result = profileRepositoryCustom.findProfileCountByIdAndName(1L, "F1soft");
        assertThat(result).isEqualTo(ZERO);
    }

    @Test
    public void findProfileCountByIdAndName_Should_Return_1() {
        BigInteger result = profileRepositoryCustom.findProfileCountByIdAndName(2L, "F1soft Profile");
        assertThat(result).isEqualTo(ONE);
    }

}