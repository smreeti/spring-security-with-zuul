package com.cogent.service;

import com.cogent.controller.subDepartmentController.dto.requestDTO.SubDepartmentRequestDTO;
import com.cogent.modal.SubDepartment;

import java.util.Optional;

/**
 * @author Sauravi
 */

public interface SubDepartmentService {
    Optional<SubDepartment> createSubDepartment(SubDepartmentRequestDTO subDepartmentRequestDTO);

}
