package com.cogent.service;

import com.cogent.controller.subDepartmentController.dto.requestDTO.SubDepartmentRequestDTO;
import com.cogent.modal.SubDepartment;

import java.util.List;
import java.util.Optional;

/**
 * @author Sauravi
 */

public interface SubDepartmentService {
    SubDepartment createSubDepartment(SubDepartmentRequestDTO subDepartmentRequestDTO);

    List<SubDepartment> fetchSubDepartments();
}
