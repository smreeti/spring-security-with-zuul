package com.cogent.repository;

import com.cogent.modal.SubDepartment;
import com.cogent.repository.customRepository.SubDepartmentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sauravi
 */

@Repository
@Transactional
public interface SubDepartmentRepository extends JpaRepository<SubDepartment, Long>, SubDepartmentRepositoryCustom {

    @Query(value = "SELECT COUNT(id) FROM sub_department WHERE name=:name AND status='Y'", nativeQuery = true)
    Integer findByName(@Param("name") String name);

    @Query(value = "SELECT COUNT(id) FROM sub_department WHERE code=:code AND status='Y'", nativeQuery = true)
    Integer findByCode(@Param("code") String code);

    @Query(value = "SELECT * FROM sub_department WHERE name=:name AND status='Y'", nativeQuery = true)
    SubDepartment findSubDepartment(@Param("name") String name);

    @Query(value = "SELECT sd FROM SubDepartment sd WHERE sd.name=:name AND sd.status='Y'")
    SubDepartment findSubDepartmentByNameJPA(@Param("name") String name);


    SubDepartment findSubDepartmentByName(String name);
}
