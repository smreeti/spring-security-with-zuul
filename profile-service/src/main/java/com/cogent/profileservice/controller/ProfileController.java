package com.cogent.profileservice.controller;

import com.cogent.profileservice.ProfileRequestDTO;
import com.cogent.profileservice.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cogent.profileservice.constants.WebResourceKeyConstants.BASE_API;
import static com.cogent.profileservice.constants.WebResourceKeyConstants.SAVE;
import static org.springframework.http.ResponseEntity.*;

/**
 * @author smriti on 7/2/19
 */
@RestController
@RequestMapping(value = BASE_API)
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping(value = SAVE)
    public ResponseEntity<?> saveProfile(@RequestBody ProfileRequestDTO requestDTO){
        return ok(profileService.saveProfile(requestDTO));
    }

}
