package com.f1soft.profileservice.utils;

import com.f1soft.profileservice.entities.Profile;
import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;

import java.util.Arrays;
import java.util.List;

/**
 * @author smriti on 7/12/19
 */

public class ProfileResponseUtils {

    public static List<ProfileMinimalResponseDTO> getProfileMinimalResponseList() {
        return Arrays.asList(
                new ProfileMinimalResponseDTO(1L, "F1soft", 'Y', 1L, 2L),
                new ProfileMinimalResponseDTO(2L, "Cogent", 'Y', 1L, 3L));
    }

}
