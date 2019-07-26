package com.cogent.repository;

import com.cogent.modal.Department;
import com.cogent.repository.customRepository.DepartmentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Sauravi
 */

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>, DepartmentRepositoryCustom {

    @Query(value = "SELECT * from department WHERE department_name=:name AND status='Y'", nativeQuery = true)
    Department findByName(@Param("name") String name);

    @Query(value = "SELECT * FROM department WHERE code=:code AND status='Y'", nativeQuery = true)
    Department findByCode(@Param("code") String code);

    @Query(value = "SELECT * FROM department WHERE status='Y'", nativeQuery = true)
    Optional<List<Department>> fetchAllDepartment();

    @Query(value = "SELECT * FROM department WHERE id=:id AND status='Y'", nativeQuery = true)
    Department findByDepartmentId(@Param("id") Long id);
}
