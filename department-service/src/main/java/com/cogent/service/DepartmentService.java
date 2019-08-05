package com.cogent.service;


import com.cogent.dto.request.Department.DepartmentRequestDTO;
import com.cogent.dto.response.DepartmentResponseDTO;
import com.cogent.modal.Department;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

/**
 * @author Sauravi
 */

public interface DepartmentService {
    void createDepartment(DepartmentRequestDTO departmentRequestDto);

    List<DepartmentResponseDTO> fetchMinimalDepartmentData();

    List<Department> fetchAllDepartment();

    Department deleteDepartment(Long id);

    Department updateDepartment(DepartmentRequestDTO departmentRequestDto);

    Department findById(Long id);

    Page<Department> fetchDepartmentDataWithpagination();

    void executeGridObjectListDemo() throws IOException;
}
