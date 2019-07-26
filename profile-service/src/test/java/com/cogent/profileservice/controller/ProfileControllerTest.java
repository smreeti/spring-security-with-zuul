package com.cogent.profileservice.controller;

import com.cogent.profileservice.dto.requestDTO.ProfileDTO;
import com.cogent.profileservice.dto.requestDTO.ProfileRequestDTO;
import com.cogent.profileservice.service.ProfileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.cogent.profileservice.constants.WebResourceKeyConstants.*;
import static com.cogent.profileservice.utils.ProfileRequestUtils.*;
import static com.cogent.profileservice.utils.ProfileResponseUtils.getProfileDetailResponse;
import static com.cogent.profileservice.utils.ProfileResponseUtils.getProfileMinimalResponseList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author smriti on 7/2/19
 */
public class ProfileControllerTest {

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController profileController;

    private static MockMvc mockMvc;

    @Before
    public void createMock() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
    }

    @Test
    public void saveProfile() throws Exception {
        String URL = BASE_API + BASE_PROFILE + SAVE;

        doNothing().when(profileService).createProfile(any(ProfileRequestDTO.class));

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeObjectToJson(getProfileRequestDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        verify(profileService, times(1)).createProfile(any(ProfileRequestDTO.class));
        verifyNoMoreInteractions(profileService);
    }

    @Test
    public void updateProfile() throws Exception {
        String URL = BASE_API + BASE_PROFILE + UPDATE;

        doNothing().when(profileService).updateProfile(any(ProfileRequestDTO.class));

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .content(writeObjectToJson(getProfileRequestDTOForUpdate()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        verify(profileService, times(1)).updateProfile(any(ProfileRequestDTO.class));
        verifyNoMoreInteractions(profileService);
    }

    @Test
    public void deleteProfile() throws Exception {
        String URL = BASE_API + BASE_PROFILE + DELETE + ID_PATH_VARIABLE_BASE;

        doNothing().when(profileService).deleteProfile(1L);

        mockMvc.perform(get(URL, 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        verify(profileService, times(1)).deleteProfile(1L);
        verifyNoMoreInteractions(profileService);
    }

    @Test
    public void searchProfile() throws Exception {
        String URL = BASE_API + BASE_PROFILE + SEARCH;

        given(profileService.searchProfile(any(ProfileDTO.class))).willReturn(getProfileMinimalResponseList());

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeObjectToJson(getProfileDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(getProfileMinimalResponseList().size())))
                .andReturn();

        verify(profileService, times(1)).searchProfile(any(ProfileDTO.class));
        verifyNoMoreInteractions(profileService);
    }

    @Test
    public void fetchProfileDetails() throws Exception {
        String URL = BASE_API + BASE_PROFILE + DETAILS + ID_PATH_VARIABLE_BASE;

        given(profileService.fetchAllProfileDetails(1L)).willReturn(getProfileDetailResponse());

        mockMvc.perform(get(URL, 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(profileService, times(1)).fetchAllProfileDetails(1L);
        verifyNoMoreInteractions(profileService);
    }

    public static <T> String writeObjectToJson(T requestDTO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(requestDTO);
    }
}
