package com.f1soft.departmentservice.utils;

import com.f1soft.departmentservice.entities.Department;
import com.f1soft.departmentservice.requestDTO.DepartmentSetupDTO;

public class DepartmentUtils {

    public static Department convertDepartmentSetupToDepartment(DepartmentSetupDTO departmentSetupDTO){
        Department department=Department.builder()
                .departmentName(departmentSetupDTO.getDepartmentName())
                .code(departmentSetupDTO.getCode())
                .status(departmentSetupDTO.getStatus())
                .build();

        return department;
    }
}
