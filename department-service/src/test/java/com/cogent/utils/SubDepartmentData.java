package com.cogent.utils;

import com.cogent.dto.request.SubDepartment.SubDepartmentRequestDTO;
import com.cogent.dto.response.SubDepartmentResponseDTO;
import com.cogent.modal.Department;
import com.cogent.modal.SubDepartment;

import java.util.ArrayList;
import java.util.List;

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
        subDepartment.setDepartment(getDepartmentInfo());
        subDepartment.setName("Billing");
        subDepartment.setCode("BILL");
        subDepartment.setStatus('Y');
        return subDepartment;
    }
    public static SubDepartmentResponseDTO getSubDepartmentResponseDTO(){
        SubDepartmentResponseDTO subDepartmentResponseDTOpartment=new SubDepartmentResponseDTO();
        subDepartmentResponseDTOpartment.setId(1L);
        subDepartmentResponseDTOpartment .setDepartment(1L);
        subDepartmentResponseDTOpartment.setName("Billing");
        subDepartmentResponseDTOpartment.setCode("BILL");
        subDepartmentResponseDTOpartment.setStatus('Y');
        return subDepartmentResponseDTOpartment;
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

    public static List<String> getSubDepartmentNames(){
        List<String> names=new ArrayList<>();
        names.add("Billing");
        names.add("Account");
        names.add("Payment");
        return names;
    }
}
