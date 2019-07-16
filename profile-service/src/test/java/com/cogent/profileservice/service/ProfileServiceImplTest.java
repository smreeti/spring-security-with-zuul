package com.cogent.profileservice.service;

import com.cogent.profileservice.repository.ProfileMenuRepository;
import com.cogent.profileservice.repository.ProfileRepository;
import com.cogent.profileservice.service.serviceImpl.ProfileMenuServiceImpl;
import com.cogent.profileservice.service.serviceImpl.ProfileServiceImpl;
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
