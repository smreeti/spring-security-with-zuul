package com.f1soft.profileservice.repository;

import com.f1soft.profileservice.entities.Profile;
import com.f1soft.profileservice.requestDTO.ProfileDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author smriti on 7/2/19
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query(value = "SELECT * FROM profile WHERE name =:name", nativeQuery = true)
    Profile findByName(@Param("name") String name);

    @Query(value = "SELECT p FROM profile p", nativeQuery = true)
    Profile getQueryToSearchProfile(ProfileDTO profileDTO);

    Profile save(Profile profile);
}
