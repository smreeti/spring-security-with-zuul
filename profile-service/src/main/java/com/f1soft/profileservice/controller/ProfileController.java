package com.f1soft.profileservice.controller;

import com.f1soft.profileservice.requestDTO.ProfileRequestDTO;
import com.f1soft.profileservice.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.f1soft.profileservice.constants.WebResourceKeyConstants.BASE_API;
import static com.f1soft.profileservice.constants.WebResourceKeyConstants.SAVE;
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
    public ResponseEntity<?> saveProfile(@RequestBody ProfileRequestDTO requestDTO) {
        profileService.createProfile(requestDTO);
        return ok().build();
    }

    @GetMapping("/hello")
    public String shhsb(){
        return "cdsc";
    }
}
