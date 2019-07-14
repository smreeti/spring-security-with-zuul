package com.f1soft.departmentservice.controller;


import com.f1soft.departmentservice.entities.Department;
import com.f1soft.departmentservice.requestDTO.DepartmentSetupDTO;
import com.f1soft.departmentservice.requestDTO.UpdatedDepartmentDTO;
import com.f1soft.departmentservice.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.f1soft.departmentservice.constants.WebResourceConstants.BASE_API;
import static com.f1soft.departmentservice.constants.WebResourceConstants.DepartmentController.BASE_API_DEPARTMENT;
import static com.f1soft.departmentservice.constants.WebResourceConstants.DepartmentController.DEPARTMENTCRUD.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = BASE_API + BASE_API_DEPARTMENT)
@Api(value = "Contoller used for crud")
public class DepartmentController {


  private  final   DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @PostMapping(value = SAVE)
    @ApiOperation("Api to save department")
    public ResponseEntity<?> save(@ApiParam(value = "contains the department details to save")@RequestBody DepartmentSetupDTO departmentSetupDTO) {
        System.out.println("*******************");
        System.out.println(departmentService.addDepartment(departmentSetupDTO));
        Department department=departmentService.addDepartment(departmentSetupDTO);
        return ok(departmentService.addDepartment(departmentSetupDTO));
    }

    @GetMapping(value = RETRIEVE)
    @ApiOperation("Api to retrieve all departments")
    public ResponseEntity<?> retrieve(){
        return ok(departmentService.fetchAllDepartment());
    }

    @PostMapping(value = DELETE)
    public ResponseEntity<?> delete(@ApiParam(value = "contains the department id to delete")@PathVariable Long id) {
        return ok(departmentService.deleteDepartment(id));
    }

    @PostMapping(value = UPDATE)
    @ApiOperation("Api to update department")
    public ResponseEntity<?> update(@RequestBody UpdatedDepartmentDTO updatedDepartmentDTO){
        return ok(departmentService.updateDepartment(updatedDepartmentDTO));
    }

}
