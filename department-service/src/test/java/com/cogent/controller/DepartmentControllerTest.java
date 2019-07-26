package com.cogent.controller;

import com.cogent.dto.request.Department.DepartmentRequestDTO;
import com.cogent.dto.response.DepartmentResponseDTO;
import com.cogent.modal.Department;
import com.cogent.service.DepartmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.cogent.constants.WebResourceConstants.BASE_API;
import static com.cogent.constants.WebResourceConstants.DepartmentController.BASE_API_DEPARTMENT;
import static com.cogent.constants.WebResourceConstants.DepartmentController.DEPARTMENTCRUD.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    private static MockMvc mockMvc;


    @Before
    public void createMock() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }


    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


    @Test
    public void departmentCrud() throws Exception {
        save_ShouldSaveDepartment();
        retrieve_ShouldRetrieveDepartments();
        retrieveMinimalData_ShouldRetrieveMinimalDepartmentData();
        delete_ShouldDeleteDepartment();
        update_ShouldUpdateDepartment();

    }


    @Test
    public void save_ShouldSaveDepartment() throws Exception {
        String URL = BASE_API + BASE_API_DEPARTMENT + SAVE;
        System.out.println(URL);

        DepartmentRequestDTO departmentRequestDto = DepartmentRequestDTO.builder()
                .departmentName("Surgical")
                .code("SRG")
                .status('Y')
                .build();
        given(departmentService.createDepartment(any(DepartmentRequestDTO.class))).willReturn(java.util.Optional.ofNullable(getDepartment()));

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(APPLICATION_JSON_UTF8)
                .content(writeObjectToJson(departmentRequestDto)))
                .andExpect(status().isOk());

        verify(departmentService).createDepartment(any(DepartmentRequestDTO.class));


    }

    @Test
    public void retrieve_ShouldRetrieveDepartments() throws Exception {
        String URL = BASE_API + BASE_API_DEPARTMENT + RETRIEVE;
        List<Department> departmentList= Arrays.asList(getDepartment());

        given(departmentService.fetchAllDepartment()).willReturn((departmentList));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentName",
                        Matchers.is("Surgical")))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

        verify(departmentService).fetchAllDepartment();
    }

    @Test
    public void retrieveMinimalData_ShouldRetrieveMinimalDepartmentData() throws Exception {
        String URL = BASE_API + BASE_API_DEPARTMENT + RETRIEVE_MINIMAL_DATA;

        List<DepartmentResponseDTO> departmentResponseDTOS = new ArrayList<>();
        DepartmentResponseDTO departmentResponseDTO = DepartmentResponseDTO.builder()
                .id(1L)
                .departmentName("Surgical")
                .code("SRG")
                .status('Y')
                .build();
        departmentResponseDTOS.add(departmentResponseDTO);

        given(departmentService.fetchMinimalDepartmentData()).willReturn(departmentResponseDTOS);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URL)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentName",
                        Matchers.is("Surgical")))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

        verify(departmentService).fetchMinimalDepartmentData();
    }

    @Test
    public void delete_ShouldDeleteDepartment() throws Exception {
        String URL = BASE_API + BASE_API_DEPARTMENT + DELETE;

        given(departmentService.deleteDepartment(1L)).willReturn(getDeletedDepartment());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(URL, 1L)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("status",
                        Matchers.is("D")))
                .andExpect(status().isOk()).andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
        verify(departmentService).deleteDepartment(1L);
    }


    @Test
    public void update_ShouldUpdateDepartment() throws Exception {
        String URL = BASE_API + BASE_API_DEPARTMENT + UPDATE;

        DepartmentRequestDTO updatedDepartmentRequestDto = DepartmentRequestDTO.builder()
                .id(1L)
                .departmentName("Surgical")
                .code("SRG")
                .status('Y')
                .build();

        given(departmentService.updateDepartment(any(DepartmentRequestDTO.class))).willReturn(getDepartment());

        System.out.println(getDepartment());

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(APPLICATION_JSON_UTF8)
                .content(writeObjectToJson(updatedDepartmentRequestDto)))
                .andExpect(status().isOk())
                .andReturn();

        verify(departmentService).updateDepartment(any(DepartmentRequestDTO.class));

    }


    public Department getDepartment() {
        Department savedDepartment=new Department();
            savedDepartment.setId(1L);
            savedDepartment.setDepartmentName("Surgical");
            savedDepartment.setCode("SRG");
            savedDepartment.setStatus('Y');
        return savedDepartment;
    }

    public Department getDeletedDepartment() {
        Department savedDepartment = new Department();
            savedDepartment.setId(1L);
            savedDepartment.setDepartmentName("Surgical");
            savedDepartment.setCode("SRG");
            savedDepartment.setStatus('D');
        return savedDepartment;
    }


    private String writeObjectToJson(final Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}



