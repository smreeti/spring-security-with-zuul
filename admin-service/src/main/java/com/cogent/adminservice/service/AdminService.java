package com.cogent.adminservice.service;

import com.cogent.adminservice.entities.Admin;
import com.cogent.adminservice.requestDTO.AdminRequestDTO;
import com.cogent.adminservice.responseDTO.AdminResponseDTO;
import com.cogent.adminservice.responseDTO.ResponseDTO;

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
