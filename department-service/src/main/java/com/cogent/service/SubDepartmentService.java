package com.cogent.service;

import com.cogent.dto.request.SubDepartment.SubDepartmentRequestDTO;
import com.cogent.dto.response.SubDepartmentResponseDTO;
import com.cogent.modal.SubDepartment;

import java.util.List;

/**
 * @author Sauravi
 */

public interface SubDepartmentService {
    SubDepartment createSubDepartment(SubDepartmentRequestDTO subDepartmentRequestDTO);

    List<SubDepartmentResponseDTO> fetchSubDepartments();

    List<SubDepartmentResponseDTO> fetchMinimalSubDepartmentData();
}
