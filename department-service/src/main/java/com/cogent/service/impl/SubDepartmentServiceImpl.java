package com.cogent.service.impl;

import com.cogent.dto.request.SubDepartment.SubDepartmentRequestDTO;
import com.cogent.dto.response.SubDepartmentResponseDTO;
import com.cogent.exceptionHandler.BadRequestDataException;
import com.cogent.exceptionHandler.DataAlreadyExistsException;
import com.cogent.exceptionHandler.DataNotFoundException;
import com.cogent.modal.Department;
import com.cogent.modal.SubDepartment;
import com.cogent.repository.SubDepartmentRepository;
import com.cogent.service.DepartmentService;
import com.cogent.service.SubDepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.cogent.constants.ErrorMessageConstants.*;
import static com.cogent.utils.SubDepartmentUtlis.parseToSubDepartment;
import static com.cogent.utils.SubDepartmentUtlis.parseToSubDepartmentResponseDTO;

@Service
@Transactional
public class SubDepartmentServiceImpl implements SubDepartmentService {

    private final SubDepartmentRepository subDepartmentRepository;

    private final DepartmentService departmentService;

    public SubDepartmentServiceImpl(SubDepartmentRepository subDepartmentRepository, DepartmentService departmentService) {
        this.subDepartmentRepository = subDepartmentRepository;
        this.departmentService = departmentService;
    }


    @Override
    public SubDepartment createSubDepartment(SubDepartmentRequestDTO subDepartmentRequestDTO) {
        if (!Objects.isNull(subDepartmentRequestDTO)) {
            validateSubDepartmentName(subDepartmentRequestDTO.getName());
            validateSubDepartmentCode(subDepartmentRequestDTO.getCode());
            Department department = departmentService.findById(subDepartmentRequestDTO.getDepartmentId());
            SubDepartment subDepartment = parseToSubDepartment.apply(subDepartmentRequestDTO, department);
            return saveSubDepartment(subDepartment);
        }
        throw new BadRequestDataException(BAD_REQUEST);
    }

    @Override
    public List<SubDepartmentResponseDTO> fetchSubDepartments() {
        List<SubDepartment> subDepartments = subDepartmentRepository.fetchSubDepartments();
        if (subDepartments == null) {
            throw new DataNotFoundException(SUB_DEPARTMENT_NOT_FOUND);
        }
        List<SubDepartmentResponseDTO> subDepartmentResponseDTOS = parseToSubDepartmentResponseDTO.apply(subDepartments);
        return subDepartmentResponseDTOS;
    }

    @Override
    public List<SubDepartmentResponseDTO> fetchMinimalSubDepartmentData() {
        if (subDepartmentRepository.fetchMinimalSubDepartmentData() == null) {
            throw new DataNotFoundException(SUB_DEPARTMENT_NOT_FOUND);
        }
        return subDepartmentRepository.fetchMinimalSubDepartmentData();
    }

    public void validateSubDepartmentName(String name) {
        if (subDepartmentRepository.findByName(name) == 1)
            throw new DataAlreadyExistsException(SUB_DEPARTMENT_ALREADY_EXISTS_WITH_NAME + name);
    }

    public void validateSubDepartmentCode(String code) {
        if (subDepartmentRepository.findByCode(code) == 1)
            throw new DataAlreadyExistsException(SUB_DEPARTMENT_ALREADY_EXISTS_WITH_CODE + code);
    }

    public SubDepartment saveSubDepartment(SubDepartment subDepartment) {
        return subDepartmentRepository.save(subDepartment);
    }


}
