package com.f1soft.profileservice.utility;

import com.f1soft.profileservice.entities.ProfileMenu;
import com.f1soft.profileservice.requestDTO.ProfileMenuRequestDTO;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author smriti on 7/10/19
 */
public class ProfileMenuUtils {

    private static BiFunction<ProfileMenuRequestDTO, Long, ProfileMenu> convertToProfileMenuResponse =
            (rolesRequestDTO, profileId) -> ProfileMenu.builder()
                    .profileId(profileId)
                    .userMenuId(rolesRequestDTO.getUserMenuId())
                    .roleId(rolesRequestDTO.getRoleId())
                    .status('Y')
                    .build();

    public static List<ProfileMenu> convertToProfileMenu(Long profileId, List<ProfileMenuRequestDTO> requestDTO) {

        List<ProfileMenu> profileMenus = requestDTO.stream()
                .map(roles -> convertToProfileMenuResponse.apply(roles, profileId))
                .collect(Collectors.toList());

        return profileMenus;
    }
}
