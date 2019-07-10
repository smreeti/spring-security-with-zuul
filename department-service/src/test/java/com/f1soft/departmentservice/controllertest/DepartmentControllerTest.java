package com.f1soft.departmentservice.controllertest;

import com.f1soft.departmentservice.controller.DepartmentController;
import com.f1soft.departmentservice.entities.Department;
import com.f1soft.departmentservice.requestDTO.DepartmentSetupDTO;
import com.f1soft.departmentservice.service.DepartmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.f1soft.departmentservice.constants.WebResourceConstants.BASE_API;
import static com.f1soft.departmentservice.constants.WebResourceConstants.DepartmentController.BASE_API_DEPARTMENT;
import static com.f1soft.departmentservice.constants.WebResourceConstants.DepartmentController.DEPARTMENTCRUD.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    MockMvc mockMvc;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    public void save_ShouldSaveDepartment() throws Exception {
        String URL = BASE_API + BASE_API_DEPARTMENT + SAVE;
        System.out.println(URL);

        DepartmentSetupDTO departmentSetupDTO = DepartmentSetupDTO.builder()
                .departmentName("Surgical")
                .code("SRG")
                .status('Y')
                .build();

        given(departmentService.addDepartment(departmentSetupDTO)).willReturn(getDepartment());

         mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(APPLICATION_JSON_UTF8)
                .content(writeObjectToJson(departmentSetupDTO)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void retrieve_ShouldRetrieveDepartments() throws Exception {
        String URL = BASE_API + BASE_API_DEPARTMENT + RETRIEVE;
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(getDepartment());

        given(departmentService.fetchAllDepartment()).willReturn(departmentList);

        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentName",
                        Matchers.is("Surgical")))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void delete_ShouldDeleteDepartment() throws Exception {
        String URL = BASE_API + BASE_API_DEPARTMENT + DELETE;

        given(departmentService.deleteDepartment(1L)).willReturn(getDeletedDepartment());

        MvcResult mvcResult =   mockMvc.perform(MockMvcRequestBuilders.post(URL,1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("status",
                Matchers.is("D")))
                .andExpect(status().isOk()).andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }



    public Department getDepartment() {
        Department savedDepartment = Department.builder()
                .id(1L)
                .departmentName("Surgical")
                .code("SRG")
                .status('Y')
                .build();
        return savedDepartment;
    }

    public Department getDeletedDepartment() {
        Department savedDepartment = Department.builder()
                .id(1L)
                .departmentName("Surgical")
                .code("SRG")
                .status('D')
                .build();
        return savedDepartment;
    }


    private String writeObjectToJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}



