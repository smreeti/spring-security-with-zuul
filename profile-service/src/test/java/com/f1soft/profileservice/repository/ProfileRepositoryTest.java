package com.f1soft.profileservice.repository;

import com.f1soft.profileservice.entities.Profile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

/**
 * @author smriti on 7/9/19
 */
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ProfileRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    public void saveProfile() {
        Profile profile = testEntityManager.persist(new Profile(null, "Superadmin",
                "This is super admin profile", 'Y', 1L, 1L));

        assertThat(profile).hasFieldOrPropertyWithValue("name", "Superadmin");
        assertThat(profile).hasFieldOrPropertyWithValue("description", "This is super admin profile");
        assertThat(profile).hasFieldOrPropertyWithValue("departmentId", 1L);
        assertThat(profile).hasFieldOrPropertyWithValue("subDepartmentId", 1L);
    }

    @Test
    public void testFindByName() {

        testEntityManager.persist(new Profile(null, "abc", "This is super admin profile", 'Y',
                1L, 1L));
        testEntityManager.persist(new Profile(null, "xyz",
                "This is finance profile", 'Y',
                1L, 1L));

        Profile profile = profileRepository.findByName("FinAdmin");

        assertNotNull(profile.getName());
    }

}

