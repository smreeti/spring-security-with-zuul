package com.f1soft.departmentservice.repository;

import com.f1soft.departmentservice.entities.Department;
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
    Optional<Department> findByName(@Param("name") String name);

    @Query(value = "SELECT * FROM department WHERE code=:code AND status='Y'", nativeQuery = true)
    Department findByCode(@Param("code") String code);

    @Query(value = "SELECT * FROM department WHERE status='Y'", nativeQuery = true)
    Optional<List<Department>> fetchAllDepartment();

    @Query(value = "SELECT * FROM department WHERE id=:id AND status='Y'", nativeQuery = true)
    Department findByDepartmentId(@Param("id") Long id);

    @Query(value = "SELECT * FROM department WHERE id=:id AND department_name=:name AND code=:code AND status=:status", nativeQuery = true)
    Department searchDepartment(@Param("id") Long id, @Param("name") String name, @Param("code") String code, @Param("status") Character status);

}
