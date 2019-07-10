package com.f1soft.profileservice.controller;

import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.requestDTO.ProfileRequestDTO;
import com.f1soft.profileservice.requestDTO.ProfileMenuRequestDTO;
import com.f1soft.profileservice.service.ProfileService;
import com.f1soft.profileservice.utils.ProfileRequestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static com.f1soft.profileservice.constants.WebResourceKeyConstants.BASE_API;
import static com.f1soft.profileservice.constants.WebResourceKeyConstants.SAVE;
import static com.f1soft.profileservice.utils.ProfileRequestUtils.getProfileRequestDTO;
import static org.mockito.Mockito.doNothing;
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

        ProfileRequestDTO requestDTO = getProfileRequestDTO();

        doNothing().when(profileService).createProfile(requestDTO);

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeObjectToJson(requestDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    private String writeObjectToJson(ProfileRequestDTO requestDTO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(requestDTO);
    }
}
