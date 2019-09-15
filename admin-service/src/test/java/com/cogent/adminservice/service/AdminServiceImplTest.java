package com.cogent.adminservice.service;

import com.cogent.adminservice.exceptions.DataDuplicationException;
import com.cogent.adminservice.dto.request.AdminRequestDTO;
import com.cogent.adminservice.service.impl.AdminServiceImpl;
import com.cogent.adminservice.repository.AdminRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.cogent.adminservice.utils.AdminRequestUtils.*;
import static org.mockito.BDDMockito.given;

/**
 * @author smriti on 7/21/19
 */

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceImplTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private AdminRepository adminRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void saveAdmin() {
        Should_ThrowException_When_UsernameAlreadyExists();

        Should_ThrowException_When_EmailAddressAlreadyExists();
    }

    @Test
    public void Should_ThrowException_When_UsernameAlreadyExists() {
        AdminRequestDTO adminRequestDTO = getAdminRequestDTO();

        given(adminRepository.fetchAdminByUsername(adminRequestDTO.getUsername()))
                .willReturn(Optional.of(getAdmin()));

        thrown.expect(DataDuplicationException.class);

        adminService.saveAdmin(adminRequestDTO);
    }

    @Test
    public void Should_ThrowException_When_EmailAddressAlreadyExists() {
        AdminRequestDTO adminRequestDTO = getAdminRequestDTO();

        given(adminRepository.fetchAdminByEmailAddress(adminRequestDTO.getEmailAddress()))
                .willReturn(Optional.of(getAdmin()));

        thrown.expect(DataDuplicationException.class);

        adminService.saveAdmin(adminRequestDTO);
    }

    @Test
    public void Should_Successfully_SaveAdmin() {
        AdminRequestDTO adminRequestDTO = getAdminRequestDTO();

        given(adminRepository.fetchAdminByUsername(adminRequestDTO.getUsername()))
                .willReturn(Optional.empty());

        given(adminRepository.fetchAdminByEmailAddress(adminRequestDTO.getEmailAddress()))
                .willReturn(Optional.empty());

        adminService.saveAdmin(adminRequestDTO);
    }
}
