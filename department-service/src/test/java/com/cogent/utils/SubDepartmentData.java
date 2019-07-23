package com.cogent.utils;

import com.cogent.controller.subDepartmentController.dto.requestDTO.SubDepartmentRequestDTO;
import com.cogent.modal.Department;
import com.cogent.modal.SubDepartment;

public class SubDepartmentData {
    public static SubDepartmentRequestDTO getsubDepartmentRequestDto(){
       SubDepartmentRequestDTO subDepartmentRequestDTO=SubDepartmentRequestDTO.builder()
                .departmentId(1L)
                .name("Billing")
                .code("BILL")
                .status('Y').build();
       return subDepartmentRequestDTO;
    }
    public static SubDepartment getSubDepartmentInfo(){
        SubDepartment subDepartment=new SubDepartment();
        subDepartment.setId(1L);
        subDepartment .setDepartment(null);
        subDepartment.setName("Billing");
        subDepartment.setCode("BILL");
        subDepartment.setStatus('Y');
        return subDepartment;
    }

    public static SubDepartment getSubDepartmentInfoToSave(){
        SubDepartment subDepartment=new SubDepartment();
        subDepartment .setDepartment(getDepartmentInfo());
        subDepartment.setName("Billing");
        subDepartment.setCode("BILL");
        subDepartment.setStatus('Y');
        return subDepartment;
    }

    public static Department getDepartmentInfo(){
        Department department=new Department();
        department.setId(1L);
        department.setDepartmentName("Cash");
        department.setCode("CSH");
        department.setStatus('Y');
        return department;
    }
}
