package com.f1soft.departmentservice;

import com.f1soft.departmentservice.entities.Department;

import java.time.LocalDate;
/**
 * @author Sauravi
 */

public abstract class AbstractDepartmentInfo {

    public Department getDepartmentInfo(){
        return Department.builder()
                .id(null)
                .departmentName("Labrotary")
                .code("LAB")
                .status('Y')
                .createdById(1L)
                .createdDate(LocalDate.now())
                .build();
    }
}
