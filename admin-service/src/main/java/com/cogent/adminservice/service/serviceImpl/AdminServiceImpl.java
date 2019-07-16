package com.cogent.adminservice.service.serviceImpl;

import com.cogent.adminservice.constants.ErrorMessageConstants.AdminNotFoundException;
import com.cogent.adminservice.entities.Admin;
import com.cogent.adminservice.exceptions.NoContentFoundException;
import com.cogent.adminservice.repository.AdminRepository;
import com.cogent.adminservice.requestDTO.AdminRequestDTO;
import com.cogent.adminservice.responseDTO.AdminResponseDTO;
import com.cogent.adminservice.responseDTO.ResponseDTO;
import com.cogent.adminservice.service.AdminService;
import com.cogent.adminservice.utility.AdminUtils;
import com.cogent.adminservice.utility.MapperUtility;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.cogent.adminservice.utility.AdminUtils.convertToAdminResponse;
import static com.cogent.adminservice.utility.QueryCreator.createQueryToFetchAdminDetails;
import static com.cogent.adminservice.utility.QueryCreator.createQueryToFetchAdminsToSendEmail;

/**
 * @author smriti
 */

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void saveAdmin(AdminRequestDTO requestDTO) {
        Admin admin = MapperUtility.map(requestDTO, Admin.class);

        admin.setPassword(BCrypt.hashpw(requestDTO.getPassword(), BCrypt.gensalt()));
        admin.setLoginAttempt(0);
        admin.setCreatedDate(new Date());
        admin.setRoles(Arrays.asList("ROLE_USER"));
        adminRepository.save(admin);
    }

    @Override
    public AdminResponseDTO searchAdmin(AdminRequestDTO requestDTO) throws NoContentFoundException {

        List<Object[]> results = entityManager.createNativeQuery(
                createQueryToFetchAdminDetails.apply(requestDTO)).getResultList();

        if (ObjectUtils.isEmpty(results))
            throw new NoContentFoundException(AdminNotFoundException.MESSAGE, AdminNotFoundException.DEVELOPER_MESSAGE);

        return convertToAdminResponse.apply(results);
    }

    @Override
    public Admin updateAdmin(AdminRequestDTO requestDTO) {

        Admin admin = this.adminRepository.getAdminById(requestDTO.getId()).orElseThrow(() -> {
            return new NoContentFoundException(AdminNotFoundException.MESSAGE, AdminNotFoundException.DEVELOPER_MESSAGE);
        });

        admin.setStatus(requestDTO.getStatus());
        admin.setLoginAttempt(requestDTO.getLoginAttempt());
        admin.setLastModifiedDate(new Date());

        return adminRepository.save(admin);
    }

    @Override
    public Admin fetchAdminByUsername(String username) {
        return adminRepository.getAdminByUsername(username).orElseThrow(() ->
                new NoContentFoundException(AdminNotFoundException.MESSAGE,
                        AdminNotFoundException.DEVELOPER_MESSAGE));
    }







    @Override
    public List<Admin> fetchAllAdmins() {
        fetch();
        return fetchCriteria();
    }

    public List<Admin> fetch() {
        long startTime = System.currentTimeMillis();

        List<Admin> adminList = adminRepository.fetchAllAdmins();
        System.out.println(System.currentTimeMillis() - startTime);
        return adminList;
    }

    public List<Admin> fetchCriteria() {

        long startTime = System.currentTimeMillis();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Admin> criteria = builder.createQuery(Admin.class);

        Root<Admin> admins = criteria.from(Admin.class);

//
//        Join<Admin, Reservation> join = admins.join(Admin_.reservation);

//        criteria.where(builder.in(admins.get("id")).value(1));

        List<Admin> adminList = entityManager.createQuery(criteria).getResultList();

        System.out.println(System.currentTimeMillis() - startTime);
        return adminList;
    }


    @Override
    public ResponseDTO adminsToSendEmails() {

        List<Object[]> results = entityManager.createNativeQuery(
                createQueryToFetchAdminsToSendEmail.get()).getResultList();

        List<AdminResponseDTO> responseDTOS = results.stream().map(AdminUtils.convertToResponse)
                .collect(Collectors.toList());

        return ResponseDTO.builder().adminResponseDTOS(responseDTOS).build();
    }


}
