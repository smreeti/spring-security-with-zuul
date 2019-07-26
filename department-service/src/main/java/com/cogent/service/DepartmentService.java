package com.cogent.service;


import com.cogent.dto.request.Department.DepartmentRequestDTO;
import com.cogent.dto.response.DepartmentResponseDTO;
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
