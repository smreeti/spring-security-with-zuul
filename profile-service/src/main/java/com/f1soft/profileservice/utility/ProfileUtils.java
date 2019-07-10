package com.f1soft.profileservice.utility;

import com.f1soft.profileservice.entities.Profile;
import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;

import java.util.function.Function;

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
}
