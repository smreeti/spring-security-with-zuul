package com.cogent.authservice.feignInterface;

import com.cogent.authservice.responseDTO.AdminResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import static com.cogent.authservice.constants.MicroServiceConstants.*;

@FeignClient(name = ADMIN_MICROSERVICE)
@Service
@RequestMapping(value = BASE_API)
public interface AdminInterface {

    @RequestMapping(value = AdminMicroServiceConstants.FETCH_ADMIN_BY_USERNAME)
    Optional<AdminResponseDTO> fetchAdminByUsername(@PathVariable("username") String username);
}
