package com.f1soft.departmentservice.service.serviceimpl;

import com.f1soft.departmentservice.entities.Department;
import com.f1soft.departmentservice.exception.BadRequestDataException;
import com.f1soft.departmentservice.exception.DataAlreadyExistsException;
import com.f1soft.departmentservice.exception.DataNotFoundException;
import com.f1soft.departmentservice.repository.DepartmentRepository;
import com.f1soft.departmentservice.requestDTO.DepartmentSetupDTO;
import com.f1soft.departmentservice.requestDTO.UpdatedDepartmentDTO;
import com.f1soft.departmentservice.service.DepartmentService;
import com.f1soft.departmentservice.utils.DepartmentUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.f1soft.departmentservice.constants.ErrorMessageConstants.*;

/**
 * @author Sauravi
 */


@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    @Override
    public Optional<Department> addDepartment(DepartmentSetupDTO departmentSetupDTO) {
        if (departmentSetupDTO != null)
            validateDepartmentName(departmentSetupDTO.getDepartmentName());
            validateDepartmentCode(departmentSetupDTO.getCode());
            Department department = DepartmentUtils.convertDepartmentSetupToDepartment(departmentSetupDTO);
            return Optional.ofNullable(Optional.ofNullable(saveDepartment(department))
                    .orElseThrow(() -> new BadRequestDataException(BAD_REQUEST)));
    }


    @Override
    public List<Department> fetchAllDepartment() {
        return departmentRepository.fetchAllDepartment().orElseThrow(() ->
                new DataNotFoundException(DEPARTMENT_NOT_FOUND));
    }

    @Override
    public Department deleteDepartment(Long id) {
        Department departmentToDelete = departmentRepository.findByDepartmentId(id);
        if (departmentToDelete == null) {
            throw new DataNotFoundException(DEPARTMENT_NOT_FOUND);
        } else {
            Department departmentToSave = DepartmentUtils.convertDepartmentToDelete(departmentToDelete);
            return saveDepartment(departmentToSave);
        }

    }

    @Override
    public Department updateDepartment(UpdatedDepartmentDTO updatedDepartmentDTO) {
        Department savedDepartment = departmentRepository.findByDepartmentId(updatedDepartmentDTO.getId());
        if (savedDepartment == null) {
            throw new DataNotFoundException(DEPARTMENT_NOT_FOUND);
        } else {
            Department departmentToSave = DepartmentUtils.convertDepartmentToUpdate(updatedDepartmentDTO, savedDepartment);
            return saveDepartment(departmentToSave);
        }
    }

    public void validateDepartmentName(String name) {
        if (departmentRepository.findByName(name) != null) {
            throw new DataAlreadyExistsException(DEPARTMENT_ALREADY_EXISTS_WITH_NAME + name);
        }

    }

    public void validateDepartmentCode(String code) {
        if (departmentRepository.findByCode(code) != null) {
            throw new DataAlreadyExistsException(DEPARTMENT_ALREADY_EXISTS_WITH_CODE + code);
        }
    }


    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }


}
