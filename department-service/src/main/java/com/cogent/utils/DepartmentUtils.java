package com.cogent.utils;

import com.cogent.controller.departmentController.DTO.requestDTO.DepartmentRequestDTO;
import com.cogent.controller.departmentController.DTO.responseDTO.DepartmentResponseDTO;
import com.cogent.modal.Department;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Sauravi
 */

public class DepartmentUtils {


    public static Function<DepartmentRequestDTO, Department> convertdepartmentRequestDtoToDepartment = (departmentRequestDto) -> {
        Department department = Department.builder()
                .id(null)
                .departmentName(departmentRequestDto.getDepartmentName())
                .code(departmentRequestDto.getCode())
                .status(departmentRequestDto.getStatus())
                .createdDate(new Date())
                .createdById(1L)
                .build();
        return department;
    };


    public static Function<Department, Department> convertDepartmentToDelete = (departmentToDelete) -> {
        Department department = Department.builder()
                .id(departmentToDelete.getId())
                .departmentName(departmentToDelete.getDepartmentName())
                .code(departmentToDelete.getCode())
                .status('D')
                .createdDate(departmentToDelete.getCreatedDate())
                .createdById(departmentToDelete.getCreatedById())
                .lastModifiedDate(new Date())
                .modifiedById(1L)
                .build();
        return department;
    };


    public static BiFunction<DepartmentRequestDTO, Department, Department> convertDepartmentToUpdate = (departmentRequestDto, savedDepartment) -> {
        Department department = Department.builder()
                .id(savedDepartment.getId())
                .departmentName(departmentRequestDto.getDepartmentName())
                .code(departmentRequestDto.getCode())
                .status(departmentRequestDto.getStatus())
                .createdDate(savedDepartment.getCreatedDate())
                .createdById(savedDepartment.getCreatedById())
                .lastModifiedDate(new Date())
                .modifiedById(1L)
                .build();
        return department;
    };

    public static Function<List<Object[]>, List<DepartmentResponseDTO>> convertObjectToDepartmentResponseDTO = (resultList) -> {
        List<DepartmentResponseDTO> departmentResponseDTOS = new ArrayList<>();
        resultList.forEach(objects -> {
            DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
            departmentResponseDTO.setId(Long.parseLong(objects[0].toString()));
            departmentResponseDTO.setDepartmentName(objects[1].toString());
            departmentResponseDTO.setCode(objects[2].toString());
            departmentResponseDTO.setStatus(objects[3].toString().charAt(0));
            departmentResponseDTOS.add(departmentResponseDTO);
        });
        return departmentResponseDTOS;
    };
}
