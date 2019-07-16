package com.f1soft.profileservice.utils;

import com.f1soft.profileservice.entities.Profile;
import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.requestDTO.ProfileMenuRequestDTO;
import com.f1soft.profileservice.requestDTO.ProfileRequestDTO;
import com.f1soft.profileservice.responseDTO.ProfileDetailResponseDTO;
import com.f1soft.profileservice.responseDTO.ProfileMenuResponseDTO;
import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;
import com.f1soft.profileservice.responseDTO.ProfileResponseDTO;

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

    public static ProfileDetailResponseDTO getProfileDetailResponse() {
        return new ProfileDetailResponseDTO(getProfileResponse(), getProfileMenuResponse());
    }

    public static ProfileResponseDTO getProfileResponse() {
        return new ProfileResponseDTO("f1soft profile");
    }

    public static List<ProfileMenuResponseDTO> getProfileMenuResponse() {
        return Arrays.asList(
                new ProfileMenuResponseDTO(1L, 1L, 1L),
                new ProfileMenuResponseDTO(2L, 2L, 2L));
    }

}
