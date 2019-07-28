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
import com.cogent.utils.SubDepartmentUtlis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.cogent.constants.ErrorMessageConstants.*;
import static com.cogent.utils.SubDepartmentUtlis.parseToSubDepartment;

@Service
@Slf4j
@Transactional
public class SubDepartmentServiceImpl implements SubDepartmentService {

    private final SubDepartmentRepository subDepartmentRepository;

    private final DepartmentService departmentService;

    public SubDepartmentServiceImpl(SubDepartmentRepository subDepartmentRepository, DepartmentService departmentService) {
        this.subDepartmentRepository = subDepartmentRepository;
        this.departmentService = departmentService;
    }


    @Override
    public void createSubDepartment(SubDepartmentRequestDTO subDepartmentRequestDTO) {
        if (Objects.isNull(subDepartmentRequestDTO)) {
            throw new BadRequestDataException(BAD_REQUEST);
        }
//        validateSubDepartmentName(subDepartmentRequestDTO.getName());
//        validateSubDepartmentCode(subDepartmentRequestDTO.getCode());
        for (int i = 0; i <= 1000; i++) {
            Department department = departmentService.findById(subDepartmentRequestDTO.getDepartmentId());
            SubDepartment subDepartment = parseToSubDepartment.apply(subDepartmentRequestDTO, department);
            saveSubDepartment(subDepartment);
        }
    }

    @Override
    public List<SubDepartmentResponseDTO> fetchSubDepartments() {
        long startTime = System.currentTimeMillis();
//        List<SubDepartmentResponseDTO> subDepartmentResponseDTOS =  SubDepartmentUtlis.parsePaginationToSubDepartmentResponseDTO
//                .apply(subDepartmentRepository.findSubDepartmentNames(PageRequest.of(0, 9000)));
        List<SubDepartmentResponseDTO> subDepartmentResponseDTOS = subDepartmentRepository.fetchSubDepartmentData();
        log.info("Execution took {}ms", (System.currentTimeMillis() - startTime));
        if (subDepartmentResponseDTOS == null) {
            throw new DataNotFoundException(SUB_DEPARTMENT_NOT_FOUND);
        }
        return subDepartmentResponseDTOS;
    }

    @Override
    public List<SubDepartmentResponseDTO> fetchMinimalSubDepartmentData() {
        if (subDepartmentRepository.fetchMinimalSubDepartmentData() == null) {
            throw new DataNotFoundException(SUB_DEPARTMENT_NOT_FOUND);
        }
        long startTime = System.currentTimeMillis();
        subDepartmentRepository.fetchMinimalSubDepartmentData();
        log.info("Execution took {}ms", (System.currentTimeMillis() - startTime));
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
