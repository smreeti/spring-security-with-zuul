package com.f1soft.profileservice.utils;

import com.f1soft.profileservice.entities.Profile;
import com.f1soft.profileservice.entities.ProfileMenu;
import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.requestDTO.ProfileMenuRequestDTO;
import com.f1soft.profileservice.requestDTO.ProfileRequestDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author smriti on 7/10/19
 */
public class ProfileRequestUtils {
    public static ProfileRequestDTO getProfileRequestDTOThatThrowsException() {
        return new ProfileRequestDTO(
                new ProfileDTO(null, "admin", "This is super admin profile",
                        'Y', 1L, 1L), new ArrayList<>());
    }

    public static ProfileRequestDTO getProfileRequestDTO() {
        return new ProfileRequestDTO(getProfileDTO(), getProfileMenuRequestDTO());
    }

    public static ProfileDTO getProfileDTO() {
        return new ProfileDTO(null,"Superadmin", "This is super admin profile",
                'Y', 1L, 1L);
    }

    public static List<ProfileMenuRequestDTO> getProfileMenuRequestDTO() {
        return Arrays.asList(new ProfileMenuRequestDTO(1L, 10L),
                (new ProfileMenuRequestDTO(2L, 11L)));
    }

    public static Profile getProfileInfo() {
        return new Profile(1L, "Superadmin", "This is super admin profile", 'Y',
                1L, 1L);
    }

    public static List<ProfileMenu> getProfileMenu() {
        return Arrays.asList(new ProfileMenu(null, 1L, 1L, 10L, 'Y'),
                (new ProfileMenu(null, 2L, 2L, 11L, 'Y')));
    }


}
