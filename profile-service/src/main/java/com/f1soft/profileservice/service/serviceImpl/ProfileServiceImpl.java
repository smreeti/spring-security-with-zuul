package com.f1soft.profileservice.service.serviceImpl;

import com.f1soft.profileservice.constants.ErrorMessageConstants;
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
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private  ProfileRepository profileRepository;

    @Autowired
    private  ProfileMenuService profileMenuService;

//    public ProfileServiceImpl(ProfileRepository profileRepository,
//                              ProfileMenuService profileMenuService) {
//        this.profileRepository = profileRepository;
//        this.profileMenuService = profileMenuService;
//    }

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
            throw new DataDuplicationException(ErrorMessageConstants.ProfileNameDuplication.MESSAGE, ErrorMessageConstants.ProfileNameDuplication.DEVELOPER_MESSAGE);
    }

    private void validateRolesRequestSize(List<ProfileMenuRequestDTO> rolesRequestDTOS) {
        if (ObjectUtils.isEmpty(rolesRequestDTOS))
            throw new NoContentFoundException(ErrorMessageConstants.NoContentFound.MESSAGE, ErrorMessageConstants.NoContentFound.DEVELOPER_MESSAGE);
    }

//    @Override
//    public List<ProfileMinimalResponseDTO> searchProfile(ProfileDTO profileDTO) {
//
//        List<Objects[]> queryToSearchProfile = getQueryToSearchProfile(profileDTO);
//
//        return new ArrayList<>();
//
////        return results.stream().map(ProfileUtils.convertObjectToProfileResponseDTO).collect(Collectors.toList());
//
//    }

//    public List<Objects[]> getQueryToSearchProfile(ProfileDTO profileDTO) {
//
//        System.out.println("WHY IS THIS " + profileRepository);
//        if (profileRepository != null) {
//
//            System.out.println(profileMenuService.hello());
//            ;
////            profileRepository.refresh(profileDTO);
//
//        } else {
//            System.out.println("------------------------------------- THIS IS IT");
//
//        }
//
////                createNativeQuery(QueryCreator.createQueryToSearchProfile.apply(null));
//
////        List<Objects[]> results = query.getResultList();
////        System.out.println(query.getResultList());
//        return null;
////        return results;
//    }


}
