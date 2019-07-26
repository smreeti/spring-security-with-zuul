package com.cogent.controller;

import com.cogent.dto.request.SubDepartment.SubDepartmentRequestDTO;
import com.cogent.dto.response.SubDepartmentResponseDTO;
import com.cogent.modal.SubDepartment;
import com.cogent.service.SubDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cogent.constants.WebResourceConstants.BASE_API;
import static com.cogent.constants.WebResourceConstants.DepartmentController.SUBDepartmentController.BASE_API_SUB_DEPARTMENT;
import static com.cogent.constants.WebResourceConstants.DepartmentController.SUBDepartmentController.SUBDEPARTMENTCRUD.*;

@RestController
@RequestMapping(value = BASE_API + BASE_API_SUB_DEPARTMENT)
@Api(value = "Contoller used for subdepartment crud")
public class SubDepartmentContoller {

    @Autowired
    SubDepartmentService subDepartmentService;

    @PostMapping(value = SAVE)
    @ApiOperation("Api to save sub-department's details")
    public ResponseEntity<?> save(@RequestBody SubDepartmentRequestDTO subDepartmentRequestDTO) {
        return ResponseEntity.ok(subDepartmentService.createSubDepartment(subDepartmentRequestDTO));
    }

    @GetMapping(value = RETRIEVE)
    @ApiOperation("Api to retrieve sub-department's details")
    public ResponseEntity<?> fetchAllSubDepartments() {
        List<SubDepartmentResponseDTO> subDepartments = subDepartmentService.fetchSubDepartments();
        return ResponseEntity.ok(subDepartments);
    }


    @GetMapping(value = RETRIEVE_MINIMAL_DATA)
    @ApiOperation("Api to retrieve sub-department's minimal details")
    public ResponseEntity<?> fetchMinimalSubDepartmentData(){
        List<SubDepartmentResponseDTO> subDepartmentResponseDTOS=subDepartmentService.fetchMinimalSubDepartmentData();
        return ResponseEntity.ok(subDepartmentResponseDTOS);
    }
}
