package com.f1soft.profileservice.service.serviceImpl;

import com.f1soft.profileservice.entities.Profile;
import com.f1soft.profileservice.entities.ProfileMenu;
import com.f1soft.profileservice.exceptions.DataDuplicationException;
import com.f1soft.profileservice.exceptions.NoContentFoundException;
import com.f1soft.profileservice.repository.ProfileRepository;
import com.f1soft.profileservice.requestDTO.ProfileRequestDTO;
import com.f1soft.profileservice.requestDTO.ProfileMenuRequestDTO;
import com.f1soft.profileservice.service.ProfileMenuService;
import com.f1soft.profileservice.service.ProfileService;
import com.f1soft.profileservice.utility.ProfileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author smriti on 7/2/19
 */
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    private final ProfileMenuService profileMenuService;

    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMenuService profileMenuService) {
        this.profileRepository = profileRepository;
        this.profileMenuService = profileMenuService;
    }

    @Override
    public void createProfile(ProfileRequestDTO profileRequestDTO) {
        validateProfileName(profileRequestDTO.getProfileDTO().getName());

        validateRolesRequestSize(profileRequestDTO.getProfileMenuRequestDTO());

        Profile savedProfile = saveProfile(ProfileUtils.convertToProfileInfo(profileRequestDTO.getProfileDTO()));

        List<ProfileMenu> profileMenus = ProfileUtils.convertToProfileMenu(savedProfile.getId(),
                profileRequestDTO.getProfileMenuRequestDTO());

        profileMenuService.saveProfileMenu(profileMenus);
    }

    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    private void validateProfileName(String name) {
        if (!Objects.isNull(profileRepository.findByProfileName(name)))
            throw new DataDuplicationException("Profile with name '" + name + "' already exists",
                    "Profile entity returned not null");
    }

    private void validateRolesRequestSize(List<ProfileMenuRequestDTO> rolesRequestDTOS) {
        if (ObjectUtils.isEmpty(rolesRequestDTOS))
            throw new NoContentFoundException("Invalid request data. User Menus cannot be left empty.",
                    "User menu list returned null");
    }

}
