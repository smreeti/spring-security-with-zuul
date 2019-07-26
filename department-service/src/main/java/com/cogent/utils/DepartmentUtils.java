package com.cogent.utils;

import com.cogent.dto.request.Department.DepartmentRequestDTO;
import com.cogent.dto.response.DepartmentResponseDTO;
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


    public static Function<DepartmentRequestDTO, Department> convertdepartmentRequestDtoToDepartment =
            (departmentRequestDto) -> {
                Department department = new Department();
                department.setId(null);
                department.setDepartmentName(departmentRequestDto.getDepartmentName());
                department.setCode(departmentRequestDto.getCode());
                department.setStatus(departmentRequestDto.getStatus());
                department.setCreatedDate(new Date());
                department.setCreatedById(1L);
                return department;

            };

    public static Function<Department, Department> convertDepartmentToDelete = (departmentToDelete) -> {
        Department department = new Department();
            department.setId(departmentToDelete.getId());
            department.setDepartmentName(departmentToDelete.getDepartmentName());
            department.setCode(departmentToDelete.getCode());
            department.setStatus('D');
            department.setCreatedDate(departmentToDelete.getCreatedDate());
            department.setCreatedById(1L);
            department.setLastModifiedDate(new Date());
            department.setModifiedById(1L);
        return department;
    };


    public static BiFunction<DepartmentRequestDTO, Department, Department> convertDepartmentToUpdate = (departmentRequestDto, savedDepartment) -> {
        Department department = new Department();
            department.setId(savedDepartment.getId());
            department.setDepartmentName(departmentRequestDto.getDepartmentName());
            department.setCode(departmentRequestDto.getCode());
            department.setStatus(departmentRequestDto.getStatus());
            department.setCreatedDate(savedDepartment.getCreatedDate());
            department.setCreatedById(savedDepartment.getCreatedById());
            department.setLastModifiedDate(new Date());
            department.setModifiedById(1L);
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
