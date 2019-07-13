package com.f1soft.profileservice.service.serviceImpl;

import com.f1soft.profileservice.constants.ErrorMessageConstants;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        validateProfileName(profileRequestDTO.getProfileDTO().getName());

        validateProfileMenusRequestSize(profileRequestDTO.getProfileMenuRequestDTO());

        Profile savedProfile = saveProfile(ProfileUtils.convertToProfileInfo(profileRequestDTO.getProfileDTO()));

//        List<ProfileMenu> profileMenus = ProfileMenuUtils.convertToProfileMenu(savedProfile.getId(),
//                profileRequestDTO.getProfileMenuRequestDTO());
//
//        profileMenuService.saveProfileMenu(profileMenus);
    }

    @Override
    public void updateProfile(ProfileRequestDTO requestDTO) {

    }


    private void validateProfileName(String name) {
        if (!Objects.isNull(profileRepository.findByName(name)))
            throw new DataDuplicationException(ProfileNameDuplication.MESSAGE, ProfileNameDuplication.DEVELOPER_MESSAGE);
    }

    private void validateProfileMenusRequestSize(List<ProfileMenuRequestDTO> rolesRequestDTOS) {
        if (ObjectUtils.isEmpty(rolesRequestDTOS))
            throw new NoContentFoundException(ProfileMenusNotFound.MESSAGE, ProfileMenusNotFound.DEVELOPER_MESSAGE);
    }

    public Profile saveProfile(Profile profile) {
        System.out.println(profileRepository.save(profile));
        return profileRepository.save(profile);
    }
//    @Override
//    public List<ProfileMinimalResponseDTO> searchProfile(ProfileDTO profileDTO) {
//
//        List<Objects[]> queryToSearchProfile = getQueryToSearchProfile(profileDTO);
//
//        return new ArrayList<>();
//
////        return results.stream().map(ProfileUtils.convertObjectToProfileResponseDTO).collect(Collectors.toList());
//    }
//
//    public List<Objects[]> getQueryToSearchProfile(ProfileDTO profileDTO) {
//
//
//        List<Object[]> result = entityManager.createNativeQuery(QueryCreator.createQueryToSearchProfile.apply(null)).getResultList();
//
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
