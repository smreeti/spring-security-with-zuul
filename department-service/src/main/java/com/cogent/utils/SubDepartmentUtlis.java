package com.cogent.utils;

import com.cogent.controller.subDepartmentController.dto.requestDTO.SubDepartmentRequestDTO;
import com.cogent.modal.Department;
import com.cogent.modal.SubDepartment;
import com.cogent.service.DepartmentService;

import java.util.function.BiFunction;

public class SubDepartmentUtlis {

    public static SubDepartment parseToSubDepartment(SubDepartmentRequestDTO subDepartmentRequestDTO) {
        return MapperUtility.map(subDepartmentRequestDTO, SubDepartment.class);
    }

    public static BiFunction<SubDepartmentRequestDTO, DepartmentService, SubDepartment> parsaToSubDepartment =
            (subDepartmentRequestDTO, departmentService) -> {

                Department department = departmentService.findById(subDepartmentRequestDTO.getId());

                return SubDepartment.builder()
                        .id(null)
                        .departmentId(department)
                        .name(subDepartmentRequestDTO.getName())
                        .code(subDepartmentRequestDTO.getCode())
                        .status(subDepartmentRequestDTO.getStatus())
                        .build();
            };
}
