package com.f1soft.profileservice.controller;

import com.f1soft.profileservice.requestDTO.ProfileRequestDTO;
import com.f1soft.profileservice.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.f1soft.profileservice.constants.WebResourceKeyConstants.BASE_API;
import static com.f1soft.profileservice.constants.WebResourceKeyConstants.SAVE;
import static org.springframework.http.ResponseEntity.ok;

/**
 * @author smriti on 7/2/19
 */
@RestController
@RequestMapping(value = BASE_API)
@Api(value = "This is profile controller")
public class ProfileController {


    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }


    @PostMapping(value = SAVE)
    @ApiOperation(value = "Save new profile")
    public ResponseEntity<?> saveProfile(@RequestBody ProfileRequestDTO requestDTO) {
        profileService.createProfile(requestDTO);
        return ok().build();
    }

    @GetMapping("/test")
    public String sayhello() {
//        custom.refresh();
        return "sdvd";
    }


}
