package com.cogent.service.impl;

import com.cogent.controller.subDepartmentController.dto.requestDTO.SubDepartmentRequestDTO;
import com.cogent.exceptionHandler.BadRequestDataException;
import com.cogent.exceptionHandler.DataAlreadyExistsException;
import com.cogent.modal.SubDepartment;
import com.cogent.repository.SubDepartmentRepository;
import com.cogent.service.DepartmentService;
import com.cogent.service.SubDepartmentService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;
import java.util.Optional;

import static com.cogent.constants.ErrorMessageConstants.*;
import static com.cogent.utils.SubDepartmentUtlis.parsaToSubDepartment;

@Service
public class SubDepartmentServiceImpl implements SubDepartmentService {

    private final SubDepartmentRepository subDepartmentRepository;

    private final DepartmentService departmentService;

    public SubDepartmentServiceImpl(SubDepartmentRepository subDepartmentRepository, DepartmentService departmentService) {
        this.subDepartmentRepository = subDepartmentRepository;
        this.departmentService = departmentService;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<SubDepartment> createSubDepartment(SubDepartmentRequestDTO subDepartmentRequestDTO) {
        if (!Objects.isNull(subDepartmentRequestDTO)) {
            validateSubDepartmentName(subDepartmentRequestDTO.getName());
            validateSubDepartmentCode(subDepartmentRequestDTO.getCode());
            SubDepartment subDepartment=parsaToSubDepartment.apply(subDepartmentRequestDTO,departmentService);
             return Optional.of(saveSubDepartment(subDepartment));
        }
        throw new BadRequestDataException(BAD_REQUEST);
    }


    public void validateSubDepartmentName(String name) {
        if (subDepartmentRepository.findByName(name) == 1)
            throw new DataAlreadyExistsException(SUB_DEPARTMENT_ALREADY_EXISTS_WITH_NAME + name);
    }

    public void validateSubDepartmentCode(String code) {
        if (subDepartmentRepository.findByCode(code) == 1)
            throw new DataAlreadyExistsException(SUB_DEPARTMENT_ALREADY_EXISTS_WITH_CODE + code);
    }

    public SubDepartment saveSubDepartment(SubDepartment subDepartment){
        return subDepartmentRepository.save(subDepartment);
    }




}
