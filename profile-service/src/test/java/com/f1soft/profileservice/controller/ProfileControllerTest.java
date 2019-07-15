package com.f1soft.profileservice.controller;

import com.f1soft.profileservice.repository.ProfileRepositoryCustom;
import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.requestDTO.ProfileRequestDTO;
import com.f1soft.profileservice.service.ProfileService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.f1soft.profileservice.constants.WebResourceKeyConstants.*;
import static com.f1soft.profileservice.utils.ProfileRequestUtils.*;
import static com.f1soft.profileservice.utils.ProfileResponseUtils.getProfileMinimalResponseList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
//import javax.inject.Inject;

/**
 * @author smriti on 7/2/19
 */
@RunWith(SpringRunner.class)
public class ProfileControllerTest {

    @Mock
    private ProfileService profileService;

    @Mock
    private ProfileRepositoryCustom profileRepositoryCustom;

    private static MockMvc mockMvc;

    @Before
    public void createMock() {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();

        this.mockMvc = standaloneSetup(new ProfileController(profileService, profileRepositoryCustom))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .alwaysDo(print())
                .build();
    }

    @Test
    public void saveProfile() throws Exception {
        String URL = BASE_API + SAVE;

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
        String URL = BASE_API + UPDATE;

        doNothing().when(profileService).updateProfile(any(ProfileRequestDTO.class));

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeObjectToJson(getProfileRequestDTOForUpdate())))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        verify(profileService, times(1)).updateProfile(any(ProfileRequestDTO.class));
        verifyNoMoreInteractions(profileService);
    }

    @Test
    public void deleteProfile() throws Exception {
        String URL = BASE_API + DELETE + ID_PATH_VARIABLE_BASE;

        doNothing().when(profileService).deleteProfile(1L);

        mockMvc.perform(post(URL, 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        verify(profileService, times(1)).deleteProfile(1L);
        verifyNoMoreInteractions(profileService);
    }

    @Test
    public void searchProfile() throws Exception {
        String URL = BASE_API + SEARCH;

        given(profileRepositoryCustom.searchProfile(any(ProfileDTO.class)))
                .willReturn(getProfileMinimalResponseList());

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeObjectToJson(getProfileDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(getProfileMinimalResponseList().size())))
                .andReturn();

        verify(profileRepositoryCustom, times(1)).searchProfile(any(ProfileDTO.class));
        verifyNoMoreInteractions(profileRepositoryCustom);
    }

    public static <T> String writeObjectToJson(T requestDTO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return objectMapper.writeValueAsString(requestDTO);
    }
}
