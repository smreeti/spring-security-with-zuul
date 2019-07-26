package com.cogent.profileservice.utility;

import com.cogent.profileservice.entities.ProfileMenu;
import com.cogent.profileservice.dto.requestDTO.ProfileMenuRequestDTO;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author smriti on 7/10/19
 */
public class ProfileMenuUtils {

    private static BiFunction<Long, ProfileMenuRequestDTO, ProfileMenu> convertToProfileMenuResponse =
            (profileId, profileMenu) -> ProfileMenu.builder()
                    .id(profileMenu.getProfileMenuId() == null ? null : profileMenu.getProfileMenuId())
                    .profileId(profileId)
                    .userMenuId(profileMenu.getUserMenuId())
                    .roleId(profileMenu.getRoleId())
                    .status(profileMenu.getStatus())
                    .build();

    public static List<ProfileMenu> convertToProfileMenu(Long profileId, List<ProfileMenuRequestDTO> requestDTO) {

        List<ProfileMenu> profileMenus = requestDTO.stream()
                .map(profileMenu -> convertToProfileMenuResponse.apply(profileId, profileMenu))
                .collect(Collectors.toList());

        return profileMenus;
    }
}
