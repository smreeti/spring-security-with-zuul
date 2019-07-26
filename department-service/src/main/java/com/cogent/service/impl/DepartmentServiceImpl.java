package com.cogent.service.impl;

import com.cogent.dto.request.Department.DepartmentRequestDTO;
import com.cogent.dto.response.DepartmentResponseDTO;
import com.cogent.exceptionHandler.BadRequestDataException;
import com.cogent.exceptionHandler.DataAlreadyExistsException;
import com.cogent.exceptionHandler.DataNotFoundException;
import com.cogent.modal.Department;
import com.cogent.repository.DepartmentRepository;
import com.cogent.service.DepartmentService;
import com.cogent.utils.DepartmentUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.cogent.constants.ErrorMessageConstants.*;

/**
 * @author Sauravi
 */


@Service
@Slf4j
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Optional<Department> createDepartment(DepartmentRequestDTO departmentRequestDto) {
        if (departmentRequestDto != null) {
            validateDepartmentName(departmentRequestDto.getDepartmentName());
            validateDepartmentCode(departmentRequestDto.getCode());
            Department department = DepartmentUtils.convertdepartmentRequestDtoToDepartment.apply(departmentRequestDto);
            return Optional.of(saveDepartment(department));
        } else {
            throw new BadRequestDataException(BAD_REQUEST);
        }

    }

    @Override
    public List<Department> fetchAllDepartment() {
        return departmentRepository.fetchAllDepartment().orElseThrow(() ->
                new DataNotFoundException(DEPARTMENT_NOT_FOUND));
    }

    @Override
    public List<DepartmentResponseDTO> fetchMinimalDepartmentData() {
        return departmentRepository.fetchMinimalDepartmentData().orElseThrow(() ->
                new DataNotFoundException(DEPARTMENT_NOT_FOUND));
    }


    @Override
    public Department deleteDepartment(Long id) {
        Department departmentToDelete = findById(id);
        validateDepartmentInfo(departmentToDelete);
        Department departmentToSave = DepartmentUtils.convertDepartmentToDelete.apply(departmentToDelete);

        return saveDepartment(departmentToSave);
    }

    @Override
    public Department updateDepartment(DepartmentRequestDTO departmentRequestDto) {
        Department savedDepartment = findById(departmentRequestDto.getId());
        validateDepartmentInfo(savedDepartment);
        validateDepartmentName(departmentRequestDto.getDepartmentName());
        Department departmentToSave = DepartmentUtils.convertDepartmentToUpdate.apply(departmentRequestDto, savedDepartment);
        return saveDepartment(departmentToSave);

    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findByDepartmentId(id);
    }

    public void validateDepartmentName(String name) {
        if (departmentRepository.findByName(name) != null)
            throw new DataAlreadyExistsException(DEPARTMENT_ALREADY_EXISTS_WITH_NAME + name);
    }

    public void validateDepartmentCode(String code) {
        if (departmentRepository.findByCode(code) != null)
            throw new DataAlreadyExistsException(DEPARTMENT_ALREADY_EXISTS_WITH_CODE + code);
    }

    public void validateDepartmentInfo(Department department) {
        if (department == null)
            throw new DataNotFoundException(DEPARTMENT_NOT_FOUND);
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }






}
