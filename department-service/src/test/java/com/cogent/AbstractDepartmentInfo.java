package com.cogent;

import com.cogent.modal.Department;

import java.util.Date;

/**
 * @author Sauravi
 */

public abstract class AbstractDepartmentInfo {

    public Department getDepartmentInfo() {
        Department department=new Department();
            department.setId(null);
            department.setDepartmentName("Cash");
            department.setCode("CSH");
            department.setStatus('Y');
            department.setCreatedDate(new Date());
            department.setCreatedById(1L);
        return department;
    }
}
