package com.f1soft.departmentservice.service;

import com.f1soft.departmentservice.entities.Department;
import com.f1soft.departmentservice.requestDTO.DepartmentSetupDTO;

public interface DepartmentService {
    Department addDepartment(DepartmentSetupDTO departmentSetupDTO);
}
