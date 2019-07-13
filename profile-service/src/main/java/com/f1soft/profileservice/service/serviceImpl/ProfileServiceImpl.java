package com.f1soft.profileservice.service.serviceImpl;

import com.f1soft.profileservice.entities.Profile;
import com.f1soft.profileservice.entities.ProfileMenu;
import com.f1soft.profileservice.exceptions.DataDuplicationException;
import com.f1soft.profileservice.exceptions.NoContentFoundException;
import com.f1soft.profileservice.repository.ProfileRepository;
import com.f1soft.profileservice.requestDTO.ProfileMenuRequestDTO;
import com.f1soft.profileservice.requestDTO.ProfileRequestDTO;
import com.f1soft.profileservice.service.ProfileMenuService;
import com.f1soft.profileservice.service.ProfileService;
import com.f1soft.profileservice.utility.ProfileMenuUtils;
import com.f1soft.profileservice.utility.ProfileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
import java.util.List;

import static com.f1soft.profileservice.constants.ErrorMessageConstants.*;

/**
 * @author smriti on 7/2/19
 */
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    private ProfileMenuService profileMenuService;

    public ProfileServiceImpl(ProfileRepository profileRepository,
                              ProfileMenuService profileMenuService) {
        this.profileRepository = profileRepository;
        this.profileMenuService = profileMenuService;
    }

    @Override
    public void createProfile(ProfileRequestDTO profileRequestDTO) {

        validateProfileName(profileRepository.findProfileByName(profileRequestDTO.getProfileDTO().getName()));

        validateProfileMenusRequestSize(profileRequestDTO.getProfileMenuRequestDTO());

        Profile savedProfile = saveProfile(ProfileUtils.convertToProfileInfo(profileRequestDTO.getProfileDTO()));

        List<ProfileMenu> profileMenus = ProfileMenuUtils.convertToProfileMenu(savedProfile.getId(),
                profileRequestDTO.getProfileMenuRequestDTO());

        profileMenuService.saveProfileMenu(profileMenus);
    }

    @Override
    public void updateProfile(ProfileRequestDTO requestDTO) {
        Profile profile = profileRepository.findById(requestDTO.getProfileDTO().getId())
                .orElseThrow(() -> new NoContentFoundException(ProfileNotFound.MESSAGE, ProfileNotFound.DEVELOPER_MESSAGE));

        validateProfileName(profileRepository.findProfileByIdAndName(requestDTO.getProfileDTO().getId(),
                requestDTO.getProfileDTO().getName()));

        validateProfileMenusRequestSize(requestDTO.getProfileMenuRequestDTO());

        saveProfile(ProfileUtils.convertToProfileEntity.apply(requestDTO.getProfileDTO(), profile));

        List<ProfileMenu> profileMenus = ProfileMenuUtils.convertToProfileMenu(profile.getId(),
                requestDTO.getProfileMenuRequestDTO());

        profileMenuService.saveProfileMenu(profileMenus);

    }

    private void validateProfileName(BigInteger profileCount) {
        if (profileCount.intValue() > 0)
            throw new DataDuplicationException(ProfileNameDuplication.MESSAGE, ProfileNameDuplication.DEVELOPER_MESSAGE);
    }

    private void validateProfileMenusRequestSize(List<ProfileMenuRequestDTO> rolesRequestDTOS) {
        if (ObjectUtils.isEmpty(rolesRequestDTOS))
            throw new NoContentFoundException(ProfileMenusNotFound.MESSAGE, ProfileMenusNotFound.DEVELOPER_MESSAGE);
    }

    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

}
