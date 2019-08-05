package com.cogent.controller;

import com.cogent.dto.request.student.StudentRequestDTO;
import com.cogent.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.cogent.constants.WebResourceConstants.BASE_API;
import static com.cogent.constants.WebResourceConstants.DepartmentController.BASE_API_DEPARTMENT;
import static com.cogent.constants.WebResourceConstants.DepartmentController.DEPARTMENTCRUD.SAVE;

@RestController
@RequestMapping(value = BASE_API + BASE_API_DEPARTMENT)
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping(value = SAVE+"/student")
    public void save(@RequestBody StudentRequestDTO studentRequestDTO) {
        studentService.createstudent(studentRequestDTO);
    }

    @GetMapping(value = "/downloadexcel")
    public void download() throws IOException {
        studentService.executeGridObjectListDemo();
    }

}
