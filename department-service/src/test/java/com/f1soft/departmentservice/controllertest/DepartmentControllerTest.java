package com.f1soft.departmentservice.controllertest;

import com.f1soft.departmentservice.controller.DepartmentController;
import com.f1soft.departmentservice.service.DepartmentService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    MockMvc mockMvc;




}
