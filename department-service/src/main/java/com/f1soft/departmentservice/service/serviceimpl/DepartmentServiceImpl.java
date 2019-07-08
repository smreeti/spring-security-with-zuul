package com.f1soft.departmentservice.service.serviceimpl;

import com.f1soft.departmentservice.entities.Department;
import com.f1soft.departmentservice.exception.BadRequestDataException;
import com.f1soft.departmentservice.exception.DataAlreadyExistsException;
import com.f1soft.departmentservice.repository.DepartmentRepository;
import com.f1soft.departmentservice.requestDTO.DepartmentSetupDTO;
import com.f1soft.departmentservice.service.DepartmentService;
import com.f1soft.departmentservice.utils.DepartmentUtils;
import org.springframework.stereotype.Service;


@Service
public class DepartmentServiceImpl implements DepartmentService {

   private  DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    @Override
    public Department addDepartment(DepartmentSetupDTO departmentSetupDTO) {
        if(departmentSetupDTO != null){
            if(departmentRepository.findByName(departmentSetupDTO.getDepartmentName()) !=null){
                throw new DataAlreadyExistsException("DEPARTMENT WITH NAME :" + " " + departmentSetupDTO.getDepartmentName()
                        + " " + " ALREADY EXISTS");
            }
            if (departmentRepository.findByCode(departmentSetupDTO.getCode()) != null){
                throw new DataAlreadyExistsException("DEPARTMENT WITH CODE :" + " " + departmentSetupDTO.getCode()
                        + " " + " ALREADY EXISTS");
            }
            Department department= DepartmentUtils.convertDepartmentSetupToDepartment(departmentSetupDTO);
            return departmentRepository.save(department);
        }
        else {
          throw new BadRequestDataException("Bad Request.");
        }

    }
}
