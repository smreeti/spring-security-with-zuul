package com.f1soft.departmentservice.repository;

import com.f1soft.departmentservice.entities.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends CrudRepository<Department,Long> {

    @Query(value = "SELECT * FROM department WHERE department_name=:name AND status=:'Y'",nativeQuery = true)
    Department findByName(@Param("name") String name);

    @Query(value = "SELECT * FROM department WHERE code=:code AND status=:'Y'",nativeQuery = true)
    Department findByCode(@Param("code") String code);

    @Query(value = "SELECT * FROM department WHERE status=:'Y'",nativeQuery = true)
   List<Department> fetchAllDepartment();

    @Query(value="SELECT * FROM department WHERE id=:id status=:'Y'",nativeQuery = true)
    Department findByDepartmentId(@Param("id") Long id);
}
