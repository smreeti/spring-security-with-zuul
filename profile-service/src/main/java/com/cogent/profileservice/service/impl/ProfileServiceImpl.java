package com.cogent.profileservice.service.impl;

import com.cogent.profileservice.entities.Profile;
import com.cogent.profileservice.repository.ProfileRepository;
import com.cogent.profileservice.dto.requestDTO.ProfileDTO;
import com.cogent.profileservice.dto.requestDTO.ProfileMenuRequestDTO;
import com.cogent.profileservice.dto.requestDTO.ProfileRequestDTO;
import com.cogent.profileservice.dto.responseDTO.ProfileDetailResponseDTO;
import com.cogent.profileservice.dto.responseDTO.ProfileMinimalResponseDTO;
import com.cogent.profileservice.entities.ProfileMenu;
import com.cogent.profileservice.exceptions.DataDuplicationException;
import com.cogent.profileservice.exceptions.NoContentFoundException;
import com.cogent.profileservice.service.ProfileMenuService;
import com.cogent.profileservice.service.ProfileService;
import com.cogent.profileservice.utility.ProfileMenuUtils;
import com.cogent.profileservice.utility.ProfileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Supplier;

import static com.cogent.profileservice.constants.ErrorMessageConstants.*;

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
		
		validateProfileName(profileRepository.findProfileCountByName(profileRequestDTO.getProfileDTO().getName()));
		
		validateProfileMenusRequestSize(profileRequestDTO.getProfileMenuRequestDTO());
		
		Profile savedProfile = saveProfile(ProfileUtils.convertToProfileInfo(profileRequestDTO.getProfileDTO()));
		
		List<ProfileMenu> profileMenus = ProfileMenuUtils.convertToProfileMenu(savedProfile.getId(),
				profileRequestDTO.getProfileMenuRequestDTO());
		
		profileMenuService.saveProfileMenu(profileMenus);
	}
	
	@Override
	public void updateProfile(ProfileRequestDTO requestDTO) {
		Profile profile = profileRepository.findById(requestDTO.getProfileDTO().getId()).orElseThrow(() ->
				profileNotFound.get());
		
		validateProfileName(profileRepository.findProfileCountByIdAndName(requestDTO.getProfileDTO().getId(),
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
	
	@Override
	public void deleteProfile(Long id) {
		Profile profile = profileRepository.findById(id).orElseThrow(() -> profileNotFound.get());
		
		profile.setStatus('D');
		
		saveProfile(profile);
	}
	
	@Override
	public List<ProfileMinimalResponseDTO> searchProfile(ProfileDTO profileDTO) {
		return profileRepository.searchProfile(profileDTO).orElseThrow(
				() -> new NoContentFoundException(NoRecordsFound.MESSAGE, NoRecordsFound.DEVELOPER_MESSAGE));
	}
	
	@Override
	public ProfileDetailResponseDTO fetchAllProfileDetails(Long id) {
		return profileRepository.fetchAllProfileDetails(id);
	}
	
	private Supplier<NoContentFoundException> profileNotFound = () ->
			new NoContentFoundException(ProfileNotFound.MESSAGE, ProfileNotFound.DEVELOPER_MESSAGE);
}

