package com.f1soft.profileservice.utils;

import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;

import java.util.Arrays;
import java.util.List;

/**
 * @author smriti on 7/12/19
 */

public class ProfileResponseUtils {

    public static List<ProfileMinimalResponseDTO> getProfileMinimalResponseDTOS() {
        return Arrays.asList(
                new ProfileMinimalResponseDTO(1L, "Superadmin", 'Y', 1L, 1L),
                new ProfileMinimalResponseDTO(2L, "Finadmin", 'Y', 2L, 4L));
    }
}
