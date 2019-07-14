package com.f1soft.profileservice.controller;

import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.requestDTO.ProfileRequestDTO;
import com.f1soft.profileservice.requestDTO.ProfileMenuRequestDTO;
import com.f1soft.profileservice.service.ProfileService;
import com.f1soft.profileservice.utils.ProfileRequestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static com.f1soft.profileservice.constants.WebResourceKeyConstants.*;
import static com.f1soft.profileservice.utils.ProfileRequestUtils.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author smriti on 7/2/19
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ProfileController.class)
public class ProfileControllerTest {
    @MockBean
    private ProfileService profileService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void saveProfile() throws Exception {
        String URL = BASE_API + SAVE;

        doNothing().when(profileService).createProfile(getProfileRequestDTO());
        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeObjectToJson(getProfileRequestDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    private String writeObjectToJson(ProfileRequestDTO requestDTO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(requestDTO);
    }

    @Test
    public void updateProfile() throws Exception {
        String URL = BASE_API + UPDATE;

        doNothing().when(profileService).updateProfile(getProfileRequestDTOForUpdate());
        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeObjectToJson(getProfileRequestDTOForUpdate())))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    public void deleteProfile() throws Exception {
        String URL = BASE_API + DELETE + ID_PATH_VARIABLE_BASE;

        doNothing().when(profileService).deleteProfile(1L);
        mockMvc.perform(post(URL, 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }
}
