package com.f1soft.departmentservice.repositorytest;


import com.f1soft.departmentservice.repository.DepartmentRepositoryCustom;
import com.f1soft.departmentservice.repository.DepartmentRepositoryCustomImpl;
import com.f1soft.departmentservice.responseDTO.requestDTO.DepartmentResponseDTO;
import com.f1soft.departmentservice.utils.DepartmentUtils;
import com.f1soft.departmentservice.utils.QueryCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DepartmentRepositoryCustomTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DepartmentRepositoryCustom departmentRepositoryCustom;

    @Test
    public void testfetchDepartmentWithMinimalData(){
       List<DepartmentResponseDTO> departmentResponseDTOS= departmentRepositoryCustom.fetchDepartmentWithMinimalData();
        assertNotNull(departmentResponseDTOS);
    }


}
