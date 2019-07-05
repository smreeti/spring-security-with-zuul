package com.f1soft.profileservice.service;

import com.f1soft.profileservice.repository.ProfileMenuRepository;
import com.f1soft.profileservice.repository.ProfileRepository;
import com.f1soft.profileservice.service.serviceImpl.ProfileMenuServiceImpl;
import com.f1soft.profileservice.service.serviceImpl.ProfileServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author smriti on 7/2/19
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceImplTest {

    @Mock
    private ProfileMenuRepository profileMenuRepository;

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileServiceImpl profileService;

    @InjectMocks
    private ProfileMenuServiceImpl profileMenuService;

    @Before
    public void setup(){

    }



}
