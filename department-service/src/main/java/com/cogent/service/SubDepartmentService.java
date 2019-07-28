package com.cogent.service;

import com.cogent.dto.request.SubDepartment.SubDepartmentRequestDTO;
import com.cogent.dto.response.SubDepartmentResponseDTO;

import java.util.List;

/**
 * @author Sauravi
 */

public interface SubDepartmentService {
    void createSubDepartment(SubDepartmentRequestDTO subDepartmentRequestDTO);

    List<SubDepartmentResponseDTO> fetchSubDepartments();

    List<SubDepartmentResponseDTO> fetchMinimalSubDepartmentData();
}
