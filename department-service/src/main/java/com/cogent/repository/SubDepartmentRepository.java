package com.cogent.repository;

import com.cogent.modal.SubDepartment;
import com.cogent.repository.customRepository.SubDepartmentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sauravi
 */

@Repository
public interface SubDepartmentRepository extends JpaRepository<SubDepartment,Long>, SubDepartmentRepositoryCustom{
}
