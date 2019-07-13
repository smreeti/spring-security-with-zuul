package com.f1soft.profileservice.repository;

import com.f1soft.profileservice.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * @author smriti on 7/2/19
 */
@Repository
//@Qualifier("repo1")
public interface ProfileRepository extends JpaRepository<Profile, Long>, ProfileRepositoryCustom {

    @Query(value = "SELECT COUNT(p.id) FROM profile p WHERE p.name =:name AND p.status = 'Y'", nativeQuery = true)
    BigInteger findProfileByName(@Param("name") String name);

    @Query(value = "SELECT COUNT(p.id) FROM profile p WHERE p.id != :id AND p.name = :name\n" +
            "AND p.status = 'Y'", nativeQuery = true)
    BigInteger findProfileByIdAndName(@Param("id") Long id, @Param("name") String name);
}
