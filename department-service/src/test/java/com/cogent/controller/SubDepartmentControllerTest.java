package com.cogent.controller;

import com.cogent.dto.request.SubDepartment.SubDepartmentRequestDTO;
import com.cogent.dto.response.SubDepartmentResponseDTO;
import com.cogent.modal.SubDepartment;
import com.cogent.service.SubDepartmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static com.cogent.constants.WebResourceConstants.BASE_API;
import static com.cogent.constants.WebResourceConstants.DepartmentController.SUBDepartmentController.BASE_API_SUB_DEPARTMENT;
import static com.cogent.constants.WebResourceConstants.DepartmentController.SUBDepartmentController.SUBDEPARTMENTCRUD.*;
import static com.cogent.utils.SubDepartmentData.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class SubDepartmentControllerTest {
    @Mock
    SubDepartmentService subDepartmentService;

    @InjectMocks
    private SubDepartmentContoller subDepartmentContoller;

    private static MockMvc mockMvc;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


    @Before
    public void createMock() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(subDepartmentContoller).build();
    }

    @Test
    public void save_ShouldSaveSubDepartment() throws Exception {
        String URL = BASE_API + BASE_API_SUB_DEPARTMENT + SAVE;
        System.out.println(URL);

//        given(subDepartmentService.createSubDepartment(any(SubDepartmentRequestDTO.class)))
//                .willReturn(Optional.of(getSubDepartmentInfo()));

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(APPLICATION_JSON_UTF8)
                .content(writeObjectToJson(getsubDepartmentRequestDto())))
                .andExpect(status().isOk());

        verify(subDepartmentService).createSubDepartment(any(SubDepartmentRequestDTO.class));

    }

    @Test
    public void retrieve_ShouldFetchSubDepartments() throws Exception {
        String URL = BASE_API + BASE_API_SUB_DEPARTMENT + RETRIEVE;
        System.out.println(URL);
        List<SubDepartmentResponseDTO> subDepartments = Arrays.asList(getSubDepartmentResponseDTO());

        given(subDepartmentService.fetchSubDepartments()).willReturn(subDepartments);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name",
                        Matchers.is("Billing")))
                .andReturn();

        assertNotNull(mvcResult.getResponse());
        verify(subDepartmentService).fetchSubDepartments();
    }

    @Test
    public void retrieveMinimalSubDepartmentData_ShouldFetchSubDepartments() throws Exception{
        String URL = BASE_API + BASE_API_SUB_DEPARTMENT + RETRIEVE_MINIMAL_DATA;
        System.out.println(URL);
        List<SubDepartmentResponseDTO> subDepartmentResponseDTO = Arrays.asList(getSubDepartmentResponseDTO());

        given(subDepartmentService.fetchMinimalSubDepartmentData()).willReturn(subDepartmentResponseDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name",
                        Matchers.is("Billing")))
                .andReturn();

        assertNotNull(mvcResult.getResponse());
        verify(subDepartmentService).fetchMinimalSubDepartmentData();
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
