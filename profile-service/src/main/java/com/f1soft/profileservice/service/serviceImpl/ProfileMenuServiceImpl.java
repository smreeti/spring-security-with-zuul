package com.f1soft.profileservice.service.serviceImpl;

import com.f1soft.profileservice.entities.ProfileMenu;
import com.f1soft.profileservice.repository.ProfileMenuRepository;
import com.f1soft.profileservice.service.ProfileMenuService;
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
