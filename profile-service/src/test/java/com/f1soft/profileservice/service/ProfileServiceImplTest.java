package com.f1soft.profileservice.service;

import com.f1soft.profileservice.entities.Profile;
import com.f1soft.profileservice.entities.ProfileMenu;
import com.f1soft.profileservice.exceptions.DataDuplicationException;
import com.f1soft.profileservice.exceptions.NoContentFoundException;
import com.f1soft.profileservice.repository.ProfileRepository;
import com.f1soft.profileservice.requestDTO.ProfileGeneralInfoRequestDTO;
import com.f1soft.profileservice.requestDTO.ProfileRequestDTO;
import com.f1soft.profileservice.requestDTO.ProfileRolesRequestDTO;
import com.f1soft.profileservice.service.serviceImpl.ProfileMenuServiceImpl;
import com.f1soft.profileservice.service.serviceImpl.ProfileServiceImpl;
import com.f1soft.profileservice.utility.ProfileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author smriti on 7/2/19
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceImplTest {

    @InjectMocks
    private ProfileServiceImpl profileService;

    @Mock
    private ProfileMenuServiceImpl profileMenuService;

    @Mock
    private ProfileRepository profileRepository;

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

    public ProfileRequestDTO getProfileRequestDTOThatThrowsException() {

        return new ProfileRequestDTO(
                new ProfileGeneralInfoRequestDTO("admin", "This is super admin profile",
                        'Y', 1L, 1L), new ArrayList<>());
    }

    public ProfileRequestDTO getProfileRequestDTO() {
        return new ProfileRequestDTO(
                new ProfileGeneralInfoRequestDTO("Superadmin", "This is super admin profile",
                        'Y', 1L, 1L),
                Arrays.asList(new ProfileRolesRequestDTO(1L, 10L),
                        (new ProfileRolesRequestDTO(2L, 11L))
                ));
    }

    public Profile getProfileInfo() {
        return new Profile(1L, "Superadmin", "This is super admin profile", 'Y',
                1L, 1L);
    }

    public List<ProfileMenu> getProfileMenu() {
        return Arrays.asList(new ProfileMenu(null, 1L, 1L, 10L, 'Y'),
                (new ProfileMenu(null, 2L, 2L, 11L, 'Y')));
    }

    @Test
    public void Should_ThrowException_When_ProfileNameExists() {

        ProfileRequestDTO requestDTO = getProfileRequestDTOThatThrowsException();

        Profile profile = Profile.builder()
                .profileName("admin")
                .build();

        given(profileRepository.findByProfileName(requestDTO.getGeneralInfoRequestDTO().getProfileName()))
                .willReturn(profile);

        thrown.expect(DataDuplicationException.class);

        profileService.createProfile(requestDTO);
    }

    @Test
    public void Should_ThrowException_When_UserMenusIsEmpty() {

        ProfileRequestDTO profileRequestDTO = getProfileRequestDTOThatThrowsException();

        assertTrue(profileRequestDTO.getRolesRequestDTOS().isEmpty());

        thrown.expect(NoContentFoundException.class);

        profileService.createProfile(profileRequestDTO);
    }

    @Test
    public void Should_Successfully_SaveProfile() {

        ProfileRequestDTO requestDTO = getProfileRequestDTO();

        given(profileRepository.findByProfileName(requestDTO.getGeneralInfoRequestDTO().getProfileName())).willReturn(null);

        assertFalse(requestDTO.getRolesRequestDTOS().isEmpty());

        Profile expected = ProfileUtils.convertToProfileInfo(requestDTO.getGeneralInfoRequestDTO());

        given(profileService.saveProfile(expected)).willReturn(getProfileInfo());

        profileService.createProfile(requestDTO);

        verify(profileRepository, times(1)).save(expected);
    }

    @Test
    public void Should_Successfully_SaveProfileMenu() {
        ProfileRequestDTO requestDTO = getProfileRequestDTO();

        Profile profile = getProfileInfo();

        given(profileRepository.findByProfileName(requestDTO.getGeneralInfoRequestDTO().getProfileName())).willReturn(null);

        assertFalse(requestDTO.getRolesRequestDTOS().isEmpty());

        given(profileService.saveProfile(profile)).willReturn(profile);

        List<ProfileMenu> expectedProfileMenus = ProfileUtils.convertToProfileMenu(profile.getId(),
                requestDTO.getRolesRequestDTOS());

        given(profileMenuService.saveProfileMenu(expectedProfileMenus)).willReturn(getProfileMenu());

        profileService.createProfile(requestDTO);

        assertThat(profileMenuService.saveProfileMenu(expectedProfileMenus), hasSize(requestDTO.getRolesRequestDTOS().size()));
    }


}
