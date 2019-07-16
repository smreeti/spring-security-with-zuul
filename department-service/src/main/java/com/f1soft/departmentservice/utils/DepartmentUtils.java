package com.f1soft.departmentservice.utils;

import com.f1soft.departmentservice.entities.Department;
import com.f1soft.departmentservice.requestDTO.DepartmentSetupDTO;
import com.f1soft.departmentservice.requestDTO.UpdatedDepartmentDTO;
import com.f1soft.departmentservice.responseDTO.requestDTO.DepartmentResponseDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Sauravi
 */

public class DepartmentUtils {

    public static Department convertDepartmentSetupToDepartment(DepartmentSetupDTO departmentSetupDTO) {
        Department department = Department.builder()
                .departmentName(departmentSetupDTO.getDepartmentName())
                .code(departmentSetupDTO.getCode())
                .status(departmentSetupDTO.getStatus())
                .createdDate(LocalDate.now())
                .createdById(1L)
                .build();
        return department;
    }


  public static Function<Department,Department> convertDepartmentToDelete=(departmentToDelete)->{
      Department department = Department.builder()
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
  };



    public static BiFunction<UpdatedDepartmentDTO,Department,Department> convertDepartmentToUpdate= (updatedDepartmentDTO,savedDepartment) ->{
        Department department = Department.builder()
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
    };

    public static Function<List<Object[]>,List<DepartmentResponseDTO>> convertObjectToDepartmentResponseDTO=(resultList) ->{
        List<DepartmentResponseDTO> departmentResponseDTOS=new ArrayList<>();
        for (Object[] object: resultList){
            DepartmentResponseDTO departmentResponseDTO=new DepartmentResponseDTO();
            departmentResponseDTO.setId(Long.parseLong(object[0].toString()));
            departmentResponseDTO.setDepartmentName(object[1].toString());
            departmentResponseDTO.setCode(object[2].toString());
            departmentResponseDTO.setStatus(object[3].toString().charAt(0));
            departmentResponseDTOS.add(departmentResponseDTO);
        }
        return departmentResponseDTOS;
    };
}
