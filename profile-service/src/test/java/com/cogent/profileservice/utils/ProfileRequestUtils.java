package com.cogent.profileservice.utils;

import com.cogent.profileservice.dto.requestDTO.ProfileDTO;
import com.cogent.profileservice.dto.requestDTO.ProfileMenuRequestDTO;
import com.cogent.profileservice.dto.requestDTO.ProfileRequestDTO;
import com.cogent.profileservice.entities.Profile;
import com.cogent.profileservice.entities.ProfileMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author smriti on 7/10/19
 */
public class ProfileRequestUtils {

    /*FOR SAVE*/
    public static ProfileRequestDTO getProfileRequestDTOThatThrowsException() {
        return new ProfileRequestDTO(
                new ProfileDTO(null, "admin", "This is super admin profile",
                        'Y', 1L, 1L), new ArrayList<>());
    }

    public static ProfileRequestDTO getProfileRequestDTO() {
        return new ProfileRequestDTO(getProfileDTO(), getProfileMenuRequestDTO());
    }

    public static ProfileDTO getProfileDTO() {
        return new ProfileDTO(null, "", "This is super admin profile",
                null, null, null);
    }

    public static List<ProfileMenuRequestDTO> getProfileMenuRequestDTO() {
        return Arrays.asList(new ProfileMenuRequestDTO(null, 1L, 10L, 'Y'),
                (new ProfileMenuRequestDTO(null, 2L, 11L, 'Y')));
    }

    public static Profile getProfileInfo() {
        return new Profile(1L, "Superadmin", "This is super admin profile", 'Y',
                1L, 1L);
    }

    public static List<ProfileMenu> getProfileMenu() {
        return Arrays.asList(new ProfileMenu(1L, 1L, 1L, 10L, 'Y'),
                (new ProfileMenu(2L, 1L, 2L, 11L, 'Y')));
    }

    /*FOR UPDATE*/

    public static ProfileRequestDTO getProfileRequestDTOForUpdate() {
        return new ProfileRequestDTO(getProfileDTOForUpdate(), getUpdatedProfileMenuRequests());
    }

    public static ProfileDTO getProfileDTOForUpdate() {
        return new ProfileDTO(1L, "Updated name", "updated description",
                'Y', 1L, 1L);
    }

    public static List<ProfileMenuRequestDTO> getUpdatedProfileMenuRequests() {
        return Arrays.asList(new ProfileMenuRequestDTO(1L, 1L, 10L, 'N'),
                (new ProfileMenuRequestDTO(2L, 2L, 11L, 'Y')),
                new ProfileMenuRequestDTO(null, 3L, 10L, 'Y'));
    }

    public static Profile getUpdatedProfileInfo() {
        return new Profile(1L, "Updated name", "updated description", 'Y',
                1L, 1L);
    }

    public static List<ProfileMenu> getUpdatedProfileMenus() {
        return Arrays.asList(new ProfileMenu(1L, 1L, 1L, 10L, 'N'),
                new ProfileMenu(2L, 1L, 2L, 11L, 'Y'),
                new ProfileMenu(3L, 1L, 3L, 10L, 'Y'));
    }

    /*FOR DELETE*/
    public static Profile getDeletedProfileInfo(){
        return new Profile(1L, "Updated name", "updated description", 'D',
                1L, 1L);
    }
}
