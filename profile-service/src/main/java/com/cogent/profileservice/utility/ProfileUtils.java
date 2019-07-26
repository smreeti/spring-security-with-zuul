package com.cogent.profileservice.utility;

import com.cogent.profileservice.entities.Profile;
import com.cogent.profileservice.dto.requestDTO.ProfileDTO;
import com.cogent.profileservice.dto.responseDTO.ProfileDetailResponseDTO;
import com.cogent.profileservice.dto.responseDTO.ProfileMenuResponseDTO;
import com.cogent.profileservice.dto.responseDTO.ProfileMinimalResponseDTO;
import com.cogent.profileservice.dto.responseDTO.ProfileResponseDTO;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author smriti on 7/8/19
 */
public class ProfileUtils {

    public static Profile convertToProfileInfo(ProfileDTO profileDTO) {
        return MapperUtility.map(profileDTO, Profile.class);
    }

    public static Function<Object[], ProfileMinimalResponseDTO> convertObjectToProfileResponseDTO = (objects) -> {

        final Integer ID = 0;
        final Integer NAME = 1;
        final Integer STATUS = 2;
        final Integer DEPARTMENT_ID = 3;
        final Integer SUB_DEPARTMENT_ID = 4;

        return ProfileMinimalResponseDTO.builder()
                .id(Long.parseLong(objects[ID].toString()))
                .name(objects[NAME].toString())
                .status(objects[STATUS].toString().charAt(0))
                .departmentId(Long.parseLong(objects[DEPARTMENT_ID].toString()))
                .subDepartmentId(Long.parseLong(objects[SUB_DEPARTMENT_ID].toString()))
                .build();
    };

    public static BiFunction<ProfileDTO, Profile, Profile> convertToProfileEntity = (profileDTO, profile) -> {

        profile.setName(profileDTO.getName());
        profile.setDescription(profileDTO.getDescription());
        profile.setDepartmentId(profileDTO.getDepartmentId());
        profile.setSubDepartmentId(profileDTO.getSubDepartmentId());

        return profile;
    };

    public static Function<List<Object[]>, ProfileDetailResponseDTO>
            convertObjectToProfileDetailResponseDTO = (objects) -> {
        final Integer PROFILE_MENU_DETAILS = 0;
        final Integer PROFILE_DESCRIPTION = 1;

        ProfileDetailResponseDTO responseDTO = new ProfileDetailResponseDTO();

        objects.forEach(object -> {
            ProfileResponseDTO profileResponse = ProfileResponseDTO.builder()
                    .description(object[PROFILE_DESCRIPTION].toString())
                    .build();

            String[] profileDetails = object[PROFILE_MENU_DETAILS].toString().split(",");

            List<ProfileMenuResponseDTO> profileMenuResponseDTOS = Arrays.stream(profileDetails)
                    .map(profileMenu -> {
                        String[] p = profileMenu.split("-");
                        return ProfileMenuResponseDTO.builder()
                                .profileMenuId(Long.parseLong(p[0].toString()))
                                .roleId(Long.parseLong(p[1].toString()))
                                .userMenuId(Long.parseLong(p[2].toString()))
                                .build();
                    }).collect(Collectors.toList());

            responseDTO.setProfileResponseDTO(profileResponse);
            responseDTO.setProfileMenuResponseDTOS(profileMenuResponseDTOS);
        });

        return responseDTO;
    };
}