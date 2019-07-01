package com.f1soft.adminservice.service;

import com.f1soft.adminservice.entities.Admin;
import com.f1soft.adminservice.requestDTO.AdminRequestDTO;
import com.f1soft.adminservice.responseDTO.AdminResponseDTO;
import com.f1soft.adminservice.responseDTO.ResponseDTO;

import java.util.List;

/**
 * @author smriti
 */
public interface AdminService {

    void saveAdmin(AdminRequestDTO requestDTO);

    AdminResponseDTO searchAdmin(AdminRequestDTO requestDTO);

    Admin updateAdmin(AdminRequestDTO requestDTO);

    Admin fetchAdminByUsername(String username);




    ResponseDTO adminsToSendEmails();


    List<Admin> fetchAllAdmins();
}
