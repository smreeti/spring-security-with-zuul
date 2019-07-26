package com.cogent.adminservice.service;

import com.cogent.adminservice.entities.Admin;
import com.cogent.adminservice.dto.request.AdminRequestDTO;
import com.cogent.adminservice.dto.response.AdminResponseDTO;
import com.cogent.adminservice.dto.response.ResponseDTO;

/**
 * @author smriti
 */
public interface AdminService {

    void saveAdmin(AdminRequestDTO requestDTO);






    AdminResponseDTO searchAdmin(AdminRequestDTO requestDTO);

    Admin updateAdmin(AdminRequestDTO requestDTO);

    Admin fetchAdminByUsername(String username);




    ResponseDTO adminsToSendEmails();

}
