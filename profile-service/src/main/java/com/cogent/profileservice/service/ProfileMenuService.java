package com.cogent.profileservice.service;

import com.cogent.profileservice.entities.ProfileMenu;

import java.util.List;

/**
 * @author smriti on 7/6/19
 */
public interface ProfileMenuService {

    List<ProfileMenu> saveProfileMenu(List<ProfileMenu> profileMenus);

}
