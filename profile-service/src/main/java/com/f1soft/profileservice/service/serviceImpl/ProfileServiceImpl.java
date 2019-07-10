package com.f1soft.profileservice.service.serviceImpl;

import com.f1soft.profileservice.entities.Profile;
import com.f1soft.profileservice.entities.ProfileMenu;
import com.f1soft.profileservice.exceptions.DataDuplicationException;
import com.f1soft.profileservice.exceptions.NoContentFoundException;
import com.f1soft.profileservice.repository.ProfileRepository;
import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.requestDTO.ProfileMenuRequestDTO;
import com.f1soft.profileservice.requestDTO.ProfileRequestDTO;
import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;
import com.f1soft.profileservice.service.ProfileMenuService;
import com.f1soft.profileservice.service.ProfileService;
import com.f1soft.profileservice.utility.ProfileMenuUtils;
import com.f1soft.profileservice.utility.ProfileUtils;
import com.f1soft.profileservice.utility.QueryCreator;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.f1soft.profileservice.constants.ErrorMessageConstants.NoContentFound;
import static com.f1soft.profileservice.constants.ErrorMessageConstants.ProfileNameDuplication;

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

        List<ProfileMenu> profileMenus = ProfileMenuUtils.convertToProfileMenu(savedProfile.getId(),
                profileRequestDTO.getProfileMenuRequestDTO());

        profileMenuService.saveProfileMenu(profileMenus);
    }

    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    private void validateProfileName(String name) {
        if (!Objects.isNull(profileRepository.findByName(name)))
            throw new DataDuplicationException(ProfileNameDuplication.MESSAGE, ProfileNameDuplication.DEVELOPER_MESSAGE);
    }

    private void validateRolesRequestSize(List<ProfileMenuRequestDTO> rolesRequestDTOS) {
        if (ObjectUtils.isEmpty(rolesRequestDTOS))
            throw new NoContentFoundException(NoContentFound.MESSAGE, NoContentFound.DEVELOPER_MESSAGE);
    }

    @Override
    public List<ProfileMinimalResponseDTO> searchProfile(ProfileDTO profileDTO) {

        List<Objects[]> queryToSearchProfile = getQueryToSearchProfile(profileDTO);

        return new ArrayList<>();

//        return results.stream().map(ProfileUtils.convertObjectToProfileResponseDTO).collect(Collectors.toList());

    }

    public List<Objects[]> getQueryToSearchProfile(ProfileDTO profileDTO) {

        Profile query = profileRepository.getQueryToSearchProfile(profileDTO);

        return null;


//        System.out.println(query.getResultList());
//
//        List<Objects[]> results = query.getResultList();

//        return results;
    }


}
