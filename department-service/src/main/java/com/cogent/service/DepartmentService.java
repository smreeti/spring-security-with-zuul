package com.cogent.service;


import com.cogent.controller.departmentController.DTO.requestDTO.DepartmentRequestDTO;
import com.cogent.controller.departmentController.DTO.responseDTO.DepartmentResponseDTO;
import com.cogent.modal.Department;

import java.util.List;
import java.util.Optional;

/**
 * @author Sauravi
 */

public interface DepartmentService {
    Optional<Department> createDepartment(DepartmentRequestDTO departmentRequestDto);

    List<DepartmentResponseDTO> fetchMinimalDepartmentData();

    List<Department> fetchAllDepartment();

    Department deleteDepartment(Long id);

    Department updateDepartment(DepartmentRequestDTO departmentRequestDto);

    Department findById(Long id);
}
