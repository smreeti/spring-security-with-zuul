package com.f1soft.profileservice.utility;

import com.f1soft.profileservice.entities.Profile;
import com.f1soft.profileservice.entities.ProfileMenu;
import com.f1soft.profileservice.requestDTO.ProfileGeneralInfoRequestDTO;
import com.f1soft.profileservice.requestDTO.ProfileRolesRequestDTO;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author smriti on 7/8/19
 */
public class ProfileUtils {

    public static Profile convertToProfileInfo(ProfileGeneralInfoRequestDTO generalInfoRequestDTO) {
        return MapperUtility.map(generalInfoRequestDTO, Profile.class);
    }

    private static BiFunction<ProfileRolesRequestDTO, Long, ProfileMenu> convertToProfileMenuResponse =
            (rolesRequestDTO, profileId) -> ProfileMenu.builder()
                    .profileId(profileId)
                    .userMenuId(rolesRequestDTO.getUserMenuId())
                    .roleId(rolesRequestDTO.getRoleId())
                    .status('Y')
                    .build();

    public static List<ProfileMenu> convertToProfileMenu(Long profileId, List<ProfileRolesRequestDTO> requestDTO) {

        List<ProfileMenu> profileMenus = requestDTO.stream()
                .map(roles -> convertToProfileMenuResponse.apply(roles, profileId))
                .collect(Collectors.toList());

        return profileMenus;
    }
}
