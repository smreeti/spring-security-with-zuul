package com.cogent.profileservice.utils;

import com.cogent.profileservice.dto.responseDTO.ProfileDetailResponseDTO;
import com.cogent.profileservice.dto.responseDTO.ProfileMenuResponseDTO;
import com.cogent.profileservice.dto.responseDTO.ProfileMinimalResponseDTO;
import com.cogent.profileservice.dto.responseDTO.ProfileResponseDTO;

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
