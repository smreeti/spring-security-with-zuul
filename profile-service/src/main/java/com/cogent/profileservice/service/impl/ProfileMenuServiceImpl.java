package com.cogent.profileservice.service.impl;

import com.cogent.profileservice.entities.ProfileMenu;
import com.cogent.profileservice.repository.ProfileMenuRepository;
import com.cogent.profileservice.service.ProfileMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author smriti on 7/2/19
 */
@Service
@Transactional
public class ProfileMenuServiceImpl implements ProfileMenuService {
    private final ProfileMenuRepository profileMenuRepository;

    public ProfileMenuServiceImpl(ProfileMenuRepository profileMenuRepository) {
        this.profileMenuRepository = profileMenuRepository;
    }

    @Override
    public List<ProfileMenu> saveProfileMenu(List<ProfileMenu> profileMenus) {
        return profileMenuRepository.saveAll(profileMenus);
    }

}
