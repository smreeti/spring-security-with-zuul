package com.cogent.adminservice.repository;

import com.cogent.adminservice.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>, AdminRepositoryCustom {

    @Query(value = "SELECT * FROM admin WHERE username = :username AND status = 'Y'", nativeQuery = true)
    Optional<Admin> fetchAdminByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM admin WHERE email_address = :emailAddress AND status = 'Y'", nativeQuery = true)
    Optional<Admin> fetchAdminByEmailAddress(@Param("emailAddress") String emailAddress);

    @Query(value = "SELECT * FROM admin WHERE id = :id", nativeQuery = true)
    Optional<Admin> getAdminById(@Param("id") Long id);

    @Query(value = "SELECT * FROM admin", nativeQuery = true)
    List<Admin> fetchAllAdmins();


}
