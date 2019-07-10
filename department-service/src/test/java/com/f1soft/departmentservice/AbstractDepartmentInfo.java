package com.f1soft.departmentservice;

import com.f1soft.departmentservice.entities.Department;

import java.time.LocalDate;

public abstract class AbstractDepartmentInfo {

    public Department getDepartmentInfo(){
        return Department.builder()
                .id(1L)
                .departmentName("Surgical")
                .code("SRG")
                .status('Y')
                .createdById(1L)
                .createdDate(LocalDate.now())
                .build();
    }
}
