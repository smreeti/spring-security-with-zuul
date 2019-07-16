package com.cogent;

import com.cogent.modal.Department;

import java.util.Date;

/**
 * @author Sauravi
 */

public abstract class AbstractDepartmentInfo {

    public Department getDepartmentInfo() {
        return Department.builder()
                .id(null)
                .departmentName("Laboratory")
                .code("LAB")
                .status('Y')
                .createdById(1L)
                .createdDate(new Date())
                .build();
    }
}
