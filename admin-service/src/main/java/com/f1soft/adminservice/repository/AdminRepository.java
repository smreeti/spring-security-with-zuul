package com.f1soft.adminservice.repository;

import com.f1soft.adminservice.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query(value = "SELECT * FROM admin WHERE username = :username", nativeQuery = true)
    Optional<Admin> getAdminByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM admin WHERE id = :id", nativeQuery = true)
    Optional<Admin> getAdminById(@Param("id") Long id);

    @Query(value = "SELECT * FROM admin", nativeQuery = true)
    List<Admin> fetchAllAdmins();
}
