package com.f1soft.departmentservice.service;

import com.f1soft.departmentservice.entities.Department;
import com.f1soft.departmentservice.requestDTO.DepartmentSetupDTO;
import com.f1soft.departmentservice.requestDTO.UpdatedDepartmentDTO;
import com.f1soft.departmentservice.responseDTO.requestDTO.DepartmentResponseDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author Sauravi
 */

public interface DepartmentService {
    Optional<Department> createDepartment(DepartmentSetupDTO departmentSetupDTO);

    List<DepartmentResponseDTO> fetchMinimalDepartmentData();

   List<Department> fetchAllDepartment();

    Department deleteDepartment(Long id);

    Department updateDepartment(UpdatedDepartmentDTO updatedDepartmentDTO);
}
