package com.cogent.adminservice.utils;

import com.cogent.adminservice.entities.Admin;
import com.cogent.adminservice.dto.request.AdminRequestDTO;

/**
 * @author smriti on 7/21/19
 */
public class AdminRequestUtils {

    public static AdminRequestDTO getAdminRequestDTO() {
        return AdminRequestDTO.builder()
                .username("cogent")
                .fullName("Cogent Pulchowk")
                .emailAddress("smriti.mool@f1soft.com")
                .profileId(1L)
                .status('Y')
                .build();
    }

    public static Admin getAdmin() {
        Admin admin = new Admin();
        admin.setUsername("cogent");
        admin.setEmailAddress("smriti.mool@f1soft.com");
        admin.setFullName("Cogent Pulchowk");
        admin.setProfileId(1L);
        admin.setStatus('Y');

        return admin;
    }
}
