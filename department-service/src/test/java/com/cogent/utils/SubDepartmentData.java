package com.cogent.utils;

import com.cogent.controller.subDepartmnetController.dto.requestDTO.SubDepartmentRequestDTO;
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
        SubDepartment subDepartment=SubDepartment.builder()
                .id(1L)
                .departmentId(Department.builder().id(1L).departmentName("Cash").code("CSH").status('Y').build())
                .name("Billing")
                .code("BILL")
                .status('Y').build();
        return subDepartment;
    }

    public static SubDepartment getSubDepartmentInfoToSave(){
        SubDepartment subDepartment=SubDepartment.builder()
                .departmentId(Department.builder().id(1L).departmentName("Cash").code("CSH").status('Y').build())
                .name("Billing")
                .code("BILL")
                .status('Y').build();
        return subDepartment;
    }

}
