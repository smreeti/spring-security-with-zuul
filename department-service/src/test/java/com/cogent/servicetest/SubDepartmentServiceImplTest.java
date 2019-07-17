package com.cogent.servicetest;

import com.cogent.repository.SubDepartmentRepository;
import com.cogent.service.SubDepartmentService;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Sauravi
 */

@RunWith(MockitoJUnitRunner.class)
public class SubDepartmentServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    SubDepartmentRepository subDepartmentRepository;

    @InjectMocks
    SubDepartmentService subDepartmentService;



}
