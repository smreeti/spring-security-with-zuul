package com.cogent.profileservice.controller;

import com.cogent.profileservice.dto.requestDTO.ProfileDTO;
import com.cogent.profileservice.dto.requestDTO.ProfileRequestDTO;
import com.cogent.profileservice.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cogent.profileservice.constants.WebResourceKeyConstants.*;
import static org.springframework.http.ResponseEntity.ok;

/**
 * @author smriti on 7/2/19
 */
@RestController
@RequestMapping(value = BASE_API + BASE_PROFILE)
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

    @PostMapping(value = UPDATE)
    @ApiOperation(value = "Update existing profile")
    public ResponseEntity<Void> updateProfile(@RequestBody ProfileRequestDTO requestDTO) {
        profileService.updateProfile(requestDTO);
        return ok().build();
    }

    @GetMapping(value = DELETE + ID_PATH_VARIABLE_BASE)
    @ApiOperation(value = "Set profile status as 'D' when deleted")
    public ResponseEntity<?> deleteProfile(@PathVariable("id") Long id) {
        profileService.deleteProfile(id);
        return ok().build();
    }

    @PostMapping(value = SEARCH)
    @ApiOperation(value = "Search profile according to given request parameters")
    public ResponseEntity<?> searchProfile(@RequestBody ProfileDTO profileDTO) {
        return ok(profileService.searchProfile(profileDTO));
    }

    @GetMapping(value = DETAILS + ID_PATH_VARIABLE_BASE)
    @ApiOperation(value = "Fetch all profile details ")
    public ResponseEntity<?> fetchAllProfileDetails(@PathVariable("id") Long id) {
        return ok(profileService.fetchAllProfileDetails(id));
    }
}