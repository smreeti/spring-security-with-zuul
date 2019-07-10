package com.f1soft.profileservice.repository;

import com.f1soft.profileservice.entities.Profile;
import com.f1soft.profileservice.repository.custom.ProfileRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author smriti on 7/2/19
 */
@Repository
//@Qualifier("repo1")
public interface ProfileRepository extends JpaRepository<Profile, Long>, ProfileRepositoryCustom{

    @Query(value = "SELECT * FROM profile WHERE name =:name", nativeQuery = true)
    Profile findByName(@Param("name") String name);
}
