package com.cogent.utils;

import com.cogent.DTO.requestDTO.DepartmentSetupDTO;
import com.cogent.DTO.requestDTO.UpdatedDepartmentDTO;
import com.cogent.DTO.responseDTO.DepartmentResponseDTO;
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


    public static Function<DepartmentSetupDTO, Department> convertDepartmentSetupDtoToDepartment = (departmentSetupDTO) -> {
        Department department = Department.builder()
                .departmentName(departmentSetupDTO.getDepartmentName())
                .code(departmentSetupDTO.getCode())
                .status(departmentSetupDTO.getStatus())
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


    public static BiFunction<UpdatedDepartmentDTO, Department, Department> convertDepartmentToUpdate = (updatedDepartmentDTO, savedDepartment) -> {
        Department department = Department.builder()
                .id(savedDepartment.getId())
                .departmentName(updatedDepartmentDTO.getDepartmentName())
                .code(updatedDepartmentDTO.getCode())
                .status(updatedDepartmentDTO.getStatus())
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
