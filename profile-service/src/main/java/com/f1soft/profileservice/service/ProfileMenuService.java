package com.f1soft.profileservice.service;

import com.f1soft.profileservice.entities.ProfileMenu;

import java.util.List;

/**
 * @author smriti on 7/6/19
 */
public interface ProfileMenuService {

    List<ProfileMenu> saveProfileMenu(List<ProfileMenu> profileMenus);
}
