package com.cogent.adminservice.utility;

import com.cogent.adminservice.dto.response.AdminResponseDTO;

import java.util.List;
import java.util.function.Function;

public class AdminUtils {

    public static Function<Object[], AdminResponseDTO> convertToResponse = (object) -> {
        final Integer ADMIN_ID = 0;
        final Integer EMAIL_ADDRESS_INDEX = 1;

        return AdminResponseDTO.builder()
                .id(Long.parseLong(object[ADMIN_ID].toString()))
                .emailAddress(object[EMAIL_ADDRESS_INDEX].toString())
                .build();
    };

    public static Function<List<Object[]>, AdminResponseDTO> convertToAdminResponse = (objects) -> {
        final Integer ID = 0;
        final Integer PASSWORD = 1;
        final Integer STATUS = 2;
        final Integer LOGIN_ATTEMPT = 3;
        final Integer EMAIL_ADDRESS = 4;

        AdminResponseDTO responseDTO = new AdminResponseDTO();

        objects.forEach(object -> {
            responseDTO.setId(Long.parseLong(object[ID].toString()));
            responseDTO.setPassword(object[PASSWORD].toString());
            responseDTO.setStatus(object[STATUS].toString().charAt(0));
            responseDTO.setLoginAttempt(Integer.parseInt(object[LOGIN_ATTEMPT].toString()));
            responseDTO.setEmailAddress(object[EMAIL_ADDRESS].toString());
        });

        return responseDTO;
    };


}