package com.cogent.controller;



import com.cogent.dto.request.Department.DepartmentRequestDTO;
import com.cogent.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cogent.constants.WebResourceConstants.BASE_API;
import static com.cogent.constants.WebResourceConstants.DepartmentController.BASE_API_DEPARTMENT;
import static com.cogent.constants.WebResourceConstants.DepartmentController.DEPARTMENTCRUD.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = BASE_API + BASE_API_DEPARTMENT)
@Api(value = "Contoller used for crud")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping(value = SAVE)
    @ApiOperation("Api to save department's details")
    public ResponseEntity<?> save(@RequestBody DepartmentRequestDTO departmentRequestDto) {
        return ok(departmentService.createDepartment(departmentRequestDto));
    }

    @GetMapping(value = RETRIEVE)
    @ApiOperation("Api to retrieve all departments")
    public ResponseEntity<?> retrieve() {
        return ok(departmentService.fetchAllDepartment());
    }

    @GetMapping(value = RETRIEVE_MINIMAL_DATA)
    @ApiOperation("Api to retrieve all departments with minimal datas")
    public ResponseEntity<?> retrieveMinimalDepartmentData() {
        return ok(departmentService.fetchMinimalDepartmentData());
    }

    @PostMapping(value = DELETE)
    public ResponseEntity<?> delete(@ApiParam(value = "contains the department id to delete") @PathVariable Long id) {
        return ok(departmentService.deleteDepartment(id));
    }

    @PostMapping(value = UPDATE)
    @ApiOperation("Api to update department")
    public ResponseEntity<?> update(@RequestBody DepartmentRequestDTO departmentRequestDto) {
        return ok(departmentService.updateDepartment(departmentRequestDto));
    }


}
