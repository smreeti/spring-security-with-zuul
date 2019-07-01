package com.f1soft.loginservice.feignInterface;

import com.f1soft.loginservice.constants.MicroServiceConstants;
import com.f1soft.loginservice.constants.MicroServiceConstants.*;
import com.f1soft.loginservice.requestDTO.AdminRequestDTO;
import com.f1soft.loginservice.responseDTO.AdminResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = AdminMicroServiceConstants.BASE)
@Service
@RequestMapping(value = MicroServiceConstants.BASE_API)
public interface AdminInterface {

    @RequestMapping(value = AdminMicroServiceConstants.SEARCH_ADMIN)
    AdminResponseDTO searchAdmin(@RequestBody AdminRequestDTO requestDTO);

    @RequestMapping(value = AdminMicroServiceConstants.UPDATE_ADMIN)
    void updateAdmin(@RequestBody AdminResponseDTO responseDTO);
}
