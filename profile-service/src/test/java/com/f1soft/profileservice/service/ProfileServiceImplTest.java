package com.f1soft.profileservice.service;

import com.f1soft.profileservice.entities.Profile;
import com.f1soft.profileservice.entities.ProfileMenu;
import com.f1soft.profileservice.exceptions.DataDuplicationException;
import com.f1soft.profileservice.exceptions.NoContentFoundException;
import com.f1soft.profileservice.repository.ProfileRepository;
import com.f1soft.profileservice.requestDTO.ProfileRequestDTO;
import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;
import com.f1soft.profileservice.service.serviceImpl.ProfileMenuServiceImpl;
import com.f1soft.profileservice.service.serviceImpl.ProfileServiceImpl;
import com.f1soft.profileservice.utility.ProfileMenuUtils;
import com.f1soft.profileservice.utility.ProfileUtils;
import com.f1soft.profileservice.utils.ProfileResponseUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static com.f1soft.profileservice.utils.ProfileRequestUtils.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author smriti on 7/2/19
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceImplTest {

    @InjectMocks
    private ProfileServiceImpl profileService;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private ProfileMenuServiceImpl profileMenuService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {

    }

    @Test
    public void saveProfile() {
        Should_ThrowException_When_ProfileNameExists();

        Should_ThrowException_When_UserMenusIsEmpty();

        Should_Successfully_SaveProfile();

        Should_Successfully_SaveProfileMenu();
    }

    @Test
    public void updateProfile() {
        Should_ThrowException_When_ProfileIsNotFound();
    }

    @Test
    public void Should_ThrowException_When_ProfileNameExists() {

        ProfileRequestDTO requestDTO = getProfileRequestDTOThatThrowsException();

        Profile profile = Profile.builder()
                .name("admin")
                .build();

        given(profileRepository.findByName(requestDTO.getProfileDTO().getName()))
                .willReturn(profile);

        thrown.expect(DataDuplicationException.class);

        profileService.createProfile(requestDTO);
    }

    @Test
    public void Should_ThrowException_When_UserMenusIsEmpty() {

        ProfileRequestDTO profileRequestDTO = getProfileRequestDTOThatThrowsException();

        thrown.expect(NoContentFoundException.class);

        profileService.createProfile(profileRequestDTO);
    }

    @Test
    public void Should_Successfully_SaveProfile() {

        ProfileRequestDTO requestDTO = getProfileRequestDTO();

        given(profileRepository.findByName(requestDTO.getProfileDTO().getName())).willReturn(null);

        Profile expected = ProfileUtils.convertToProfileInfo(requestDTO.getProfileDTO());

        given(profileService.saveProfile(expected)).willReturn(getProfileInfo());

        profileService.createProfile(requestDTO);

//        Assertions.assertThat(profileService.saveProfile(expected)).isEqualTo(getProfileInfo());

        verify(profileRepository, times(1)).save(expected);

    }

    @Test
    public void Should_Successfully_SaveProfileMenu() {
        ProfileRequestDTO requestDTO = getProfileRequestDTO();

        Profile profile = getProfileInfo();

        given(profileRepository.findByName(requestDTO.getProfileDTO().getName())).willReturn(null);

        given(profileService.saveProfile(profile)).willReturn(profile);

        List<ProfileMenu> expectedProfileMenus = ProfileMenuUtils.convertToProfileMenu(profile.getId(),
                requestDTO.getProfileMenuRequestDTO());

        given(profileMenuService.saveProfileMenu(expectedProfileMenus)).willReturn(getProfileMenu());

        profileService.createProfile(requestDTO);

        assertThat(profileMenuService.saveProfileMenu(expectedProfileMenus),
                hasSize(requestDTO.getProfileMenuRequestDTO().size()));
    }

    @Test
    public void Should_ThrowException_When_ProfileIsNotFound() {

        ProfileRequestDTO requestDTO = getProfileRequestDTO();

        given(profileRepository.findByName(requestDTO.getProfileDTO().getName())).willReturn(null);

        thrown.expect(NoContentFoundException.class);

        profileService.updateProfile(requestDTO);
    }


}
