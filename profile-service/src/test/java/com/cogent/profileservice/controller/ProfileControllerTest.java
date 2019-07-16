package com.cogent.profileservice.controller;

import com.cogent.profileservice.ProfileRequestDTO;
import com.cogent.profileservice.entities.Profile;
import com.cogent.profileservice.service.ProfileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.cogent.profileservice.constants.WebResourceKeyConstants.BASE_API;
import static com.cogent.profileservice.constants.WebResourceKeyConstants.SAVE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author smriti on 7/2/19
 */
@WebMvcTest
@RunWith(MockitoJUnitRunner.class)
public class ProfileControllerTest {

    @InjectMocks
    private ProfileController profileController;

    @Mock
    private ProfileService profileService;

    private static MockMvc mockMvc;

    @Before
    public void createMock() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
    }

    @Test
    public void saveProfile() throws Exception {
        String URL = BASE_API + SAVE;
        ProfileRequestDTO requestDTO = ProfileRequestDTO.builder()
                .profileName("SuperAdmin Profile")
                .profileDescription("This is super admin profile")
                .active('Y')
                .departmentId(1L)
                .subDepartmentId(1L)
                .build();

        when(profileService.saveProfile(requestDTO)).thenReturn(getProfileInfo());

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeObjectToJson(requestDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    public Profile getProfileInfo() {
        return new Profile(null, 'Y', "My superadmin", "Superadmin",
                1L, 1L);
    }

    private String writeObjectToJson(ProfileRequestDTO requestDTO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(requestDTO);
    }
}
