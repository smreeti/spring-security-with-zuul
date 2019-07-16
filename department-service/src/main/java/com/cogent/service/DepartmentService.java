package com.cogent.service;

import com.cogent.DTO.requestDTO.DepartmentSetupDTO;
import com.cogent.DTO.requestDTO.UpdatedDepartmentDTO;
import com.cogent.DTO.responseDTO.DepartmentResponseDTO;
import com.cogent.modal.Department;

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
