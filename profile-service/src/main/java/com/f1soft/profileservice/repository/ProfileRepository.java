package com.f1soft.profileservice.repository;

import com.f1soft.profileservice.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author smriti on 7/2/19
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query(value = "SELECT * FROM profile WHERE name =:name", nativeQuery = true)
    Profile findByName(@Param("name") String name);

    @Query(value = "SELECT p FROM profile p", nativeQuery = true)
    List<Profile> fetchAllProfiles();
}
