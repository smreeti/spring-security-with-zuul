package com.cogent.utils;

import com.cogent.dto.request.SubDepartment.SubDepartmentRequestDTO;
import com.cogent.dto.response.DepartmentResponseDTO;
import com.cogent.dto.response.SubDepartmentResponseDTO;
import com.cogent.modal.Department;
import com.cogent.modal.SubDepartment;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SubDepartmentUtlis {

//    public static SubDepartment parseToSubDepartment(SubDepartmentRequestDTO subDepartmentRequestDTO) {
//        return MapperUtility.map(subDepartmentRequestDTO, SubDepartment.class);
//    }

    public static BiFunction<SubDepartmentRequestDTO, Department, SubDepartment> parseToSubDepartment =
            (subDepartmentRequestDTO, department) -> {
                SubDepartment subDepartment=new SubDepartment();
                subDepartment.setId(null);
                subDepartment.setDepartment(department);
                subDepartment.setName(subDepartmentRequestDTO.getName());
                subDepartment.setCode(subDepartmentRequestDTO.getCode());
                subDepartment.setStatus(subDepartmentRequestDTO.getStatus());
                return subDepartment;
            };

    public static Function<List<SubDepartment>, List<SubDepartmentResponseDTO>> parseToSubDepartmentResponseDTO =
            (subDepartmentList) -> {
                List<SubDepartmentResponseDTO> subDepartmentResponseDTOS = new ArrayList<>();
                subDepartmentList.forEach(objects -> {
                    SubDepartmentResponseDTO subDepartmentResponseDTO = new SubDepartmentResponseDTO();
                    subDepartmentResponseDTO.setId(objects.getId());
                    subDepartmentResponseDTO.setName(objects.getName());
                    subDepartmentResponseDTO.setCode(objects.getCode());
                    subDepartmentResponseDTO.setStatus(objects.getStatus());
                    subDepartmentResponseDTO.setDepartment(objects.getDepartment().getId());
                    subDepartmentResponseDTOS.add(subDepartmentResponseDTO);
                });
                return subDepartmentResponseDTOS;
            };

    public static Function<List<Object[]>, List<SubDepartmentResponseDTO>> parseObjectToSubDepartmentResponseDTO =
            (resultList) -> {
                List<SubDepartmentResponseDTO> subDepartmentResponseDTOS = new ArrayList<>();
                resultList.forEach(objects -> {
                    SubDepartmentResponseDTO  subDepartmentResponseDTO=new SubDepartmentResponseDTO();
                    subDepartmentResponseDTO.setId(Long.parseLong(objects[0].toString()));
                    subDepartmentResponseDTO.setName(objects[1].toString());
                    subDepartmentResponseDTO.setCode(objects[2].toString());
                    subDepartmentResponseDTO.setStatus(objects[3].toString().charAt(0));
                    subDepartmentResponseDTO.setDepartment(Long.parseLong(objects[4].toString()));
                    subDepartmentResponseDTOS.add(subDepartmentResponseDTO);
                });
                return subDepartmentResponseDTOS;
            };
}
