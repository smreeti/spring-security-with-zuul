package com.f1soft.departmentservice.service.serviceimpl;

import com.f1soft.departmentservice.entities.Department;
import com.f1soft.departmentservice.exception.BadRequestDataException;
import com.f1soft.departmentservice.exception.DataAlreadyExistsException;
import com.f1soft.departmentservice.exception.DataNotFoundException;
import com.f1soft.departmentservice.exception.NoChangeFoundException;
import com.f1soft.departmentservice.repository.DepartmentRepository;
import com.f1soft.departmentservice.requestDTO.DepartmentSetupDTO;
import com.f1soft.departmentservice.requestDTO.UpdatedDepartmentDTO;
import com.f1soft.departmentservice.service.DepartmentService;
import com.f1soft.departmentservice.utils.DepartmentUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Department addDepartment(DepartmentSetupDTO departmentSetupDTO) {
        if (departmentSetupDTO != null) {
            validateDepartmentName(departmentSetupDTO.getDepartmentName());
            validateDepartmentCode(departmentSetupDTO.getCode());
            Department department = DepartmentUtils.convertDepartmentSetupToDepartment(departmentSetupDTO);
            return saveDepartment(department);
        } else {
            throw new BadRequestDataException("Bad Request.");
        }

    }

    @Override
    public List<Department> fetchAllDepartment() {
//        return departmentRepository.fetchAllDepartment().orElseThrow(()-> new DataNotFoundException("SORRY, DATA NOT FOUND"));
        if (departmentRepository.fetchAllDepartment() == null) {
            throw new DataNotFoundException("SORRY, DEPARTMENT NOT FOUND");
        } else {
            return departmentRepository.fetchAllDepartment();
        }
    }

    @Override
    public Department deleteDepartment(Long id) {
        Department departmentToDelete = departmentRepository.findByDepartmentId(id);
        if (departmentToDelete == null) {
            throw new DataNotFoundException("SORRY, DEPARTMENT NOT FOUND");
        } else {
            Department departmentToSave = DepartmentUtils.convertDepartmentToDelete(departmentToDelete);
            return saveDepartment(departmentToSave);
        }

    }

    @Override
    public Department updateDepartment(UpdatedDepartmentDTO updatedDepartmentDTO) {
        Department savedDepartment = departmentRepository.findByDepartmentId(updatedDepartmentDTO.getId());
        if (savedDepartment == null) {
            throw new DataNotFoundException("SORRY, DEPARTMENT NOT FOUND");
        } else {
            searchDepartment(updatedDepartmentDTO);
            Department departmentToSave = DepartmentUtils.convertDepartmentToUpdate(updatedDepartmentDTO, savedDepartment);
            return saveDepartment(departmentToSave);
        }
    }

    public void validateDepartmentName(String name) {
        if (departmentRepository.findByName(name) != null) {
            throw new DataAlreadyExistsException("DEPARTMENT WITH NAME :" + " " + name
                    + " " + " ALREADY EXISTS");
        }

    }

    public void validateDepartmentCode(String code) {
        if (departmentRepository.findByCode(code) != null) {
            throw new DataAlreadyExistsException("DEPARTMENT WITH CODE :" + " " + code
                    + " " + " ALREADY EXISTS");
        }
    }

    public void searchDepartment(UpdatedDepartmentDTO updatedDepartmentDTO) {
        if (departmentRepository.searchDepartment(updatedDepartmentDTO.getId(), updatedDepartmentDTO.getDepartmentName(),
                updatedDepartmentDTO.getCode(), updatedDepartmentDTO.getStatus()) != null) {
            throw new NoChangeFoundException("SORRY, NO CHANGES FOUND");
        }

    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }


}
