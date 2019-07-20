package com.cogent.controller.subDepartmentController;

import com.cogent.modal.SubDepartment;
import com.cogent.repository.SubDepartmentRepository;
import com.cogent.utils.MapperUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cogent.constants.WebResourceConstants.BASE_API;
import static com.cogent.constants.WebResourceConstants.DepartmentController.SUBDepartmentController.BASE_API_SUB_DEPARTMENT;
import static com.cogent.constants.WebResourceConstants.DepartmentController.SUBDepartmentController.SUBDEPARTMENTCRUD.RETRIEVE;

@RestController
@RequestMapping(value = BASE_API + BASE_API_SUB_DEPARTMENT)
public class SubDepartmentContoller {

    @Autowired
    SubDepartmentRepository subDepartmentRepository;

    @GetMapping(value = RETRIEVE)
    @Transactional
    public SubDepartment fetchSubDepartment() {
        System.out.println("******************************************************");
        System.out.println(subDepartmentRepository.findSubDepartment("Billing"));
        System.out.println("******************************************************");
        System.out.println(MapperUtility.map(subDepartmentRepository.findSubDepartment("Billing"), SubDepartment.class));
        System.out.println("******************************************************");
        return MapperUtility.map(subDepartmentRepository.findSubDepartment("Billing"), SubDepartment.class);
    }
}
