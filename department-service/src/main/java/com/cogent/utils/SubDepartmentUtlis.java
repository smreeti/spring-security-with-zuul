package com.cogent.utils;

import com.cogent.controller.departmentController.DTO.responseDTO.DepartmentResponseDTO;
import com.cogent.controller.subDepartmentController.dto.requestDTO.SubDepartmentRequestDTO;
import com.cogent.controller.subDepartmentController.dto.responseDTO.SubDepartmentResponseDTO;
import com.cogent.modal.Department;
import com.cogent.modal.SubDepartment;
import com.cogent.service.DepartmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SubDepartmentUtlis {

    public static SubDepartment parseToSubDepartment(SubDepartmentRequestDTO subDepartmentRequestDTO) {
        return MapperUtility.map(subDepartmentRequestDTO, SubDepartment.class);
    }

    public static BiFunction<SubDepartmentRequestDTO, Department, SubDepartment> parsaToSubDepartment =
            (subDepartmentRequestDTO, department) -> {
                SubDepartment subDepartment=new SubDepartment();
                subDepartment.setId(null);
                subDepartment.setDepartment(department);
                subDepartment.setName(subDepartmentRequestDTO.getName());
                subDepartment.setCode(subDepartmentRequestDTO.getCode());
                subDepartment.setStatus(subDepartmentRequestDTO.getStatus());
                return subDepartment;
            };

    public static Function<List<SubDepartment>, List<SubDepartmentResponseDTO>> parseToSubDepartmentResponseDTO =
            (subDepartmentList) -> {
                List<SubDepartmentResponseDTO> subDepartmentResponseDTOS = new ArrayList<>();
                subDepartmentList.forEach(objects -> {
                    SubDepartmentResponseDTO subDepartmentResponseDTO = new SubDepartmentResponseDTO();
                    subDepartmentResponseDTO.setId(objects.getId());
                    subDepartmentResponseDTO.setName(objects.getName());
                    subDepartmentResponseDTO.setCode(objects.getCode());
                    subDepartmentResponseDTO.setStatus(objects.getStatus());
                    subDepartmentResponseDTO.setDepartmentId(objects.getDepartment().getId());
                    subDepartmentResponseDTOS.add(subDepartmentResponseDTO);
                });
                return subDepartmentResponseDTOS;
            };
}
