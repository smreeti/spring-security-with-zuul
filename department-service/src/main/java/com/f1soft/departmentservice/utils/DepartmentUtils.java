package com.f1soft.departmentservice.utils;

import com.f1soft.departmentservice.entities.Department;
import com.f1soft.departmentservice.requestDTO.DepartmentSetupDTO;
import com.f1soft.departmentservice.requestDTO.UpdatedDepartmentDTO;

import java.time.LocalDate;
import java.util.Date;

public class DepartmentUtils {

    public static Department convertDepartmentSetupToDepartment(DepartmentSetupDTO departmentSetupDTO){
        Department department=Department.builder()
                .departmentName(departmentSetupDTO.getDepartmentName())
                .code(departmentSetupDTO.getCode())
                .status(departmentSetupDTO.getStatus())
                .createdDate(LocalDate.now())
                .createdById(1L)
                .build();
        return department;
    }

    public static Department convertDepartmentToDelete(Department departmentToDelete){
        Department department=Department.builder()
                .id(departmentToDelete.getId())
                .departmentName(departmentToDelete.getDepartmentName())
                .code(departmentToDelete.getCode())
                .status('D')
                .createdDate(departmentToDelete.getCreatedDate())
                .createdById(departmentToDelete.getCreatedById())
                .lastModifiedDate(LocalDate.now())
                .modifiedById(1L)
                .build();
        return department;
    }

    public static Department convertDepartmentToUpdate(UpdatedDepartmentDTO updatedDepartmentDTO,Department savedDepartment){
        Department department=Department.builder()
                .id(savedDepartment.getId())
                .departmentName(updatedDepartmentDTO.getDepartmentName())
                .code(updatedDepartmentDTO.getCode())
                .status(updatedDepartmentDTO.getStatus())
                .createdDate(savedDepartment.getCreatedDate())
                .createdById(savedDepartment.getCreatedById())
                .lastModifiedDate(LocalDate.now())
                .modifiedById(1L)
                .build();
        return department;
    }
}
