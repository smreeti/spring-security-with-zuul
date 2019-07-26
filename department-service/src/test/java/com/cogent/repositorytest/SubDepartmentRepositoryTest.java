package com.cogent.repositorytest;

import com.cogent.dto.response.SubDepartmentResponseDTO;
import com.cogent.modal.SubDepartment;
import com.cogent.repository.SubDepartmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static com.cogent.utils.SubDepartmentData.getSubDepartmentInfoToSave;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class SubDepartmentRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    SubDepartmentRepository subDepartmentRepository;

//    @Test
//    public void testSaveSubDepartment() {
//        SubDepartment subDepartmentToSave = getSubDepartmentInfoToSave();
//        SubDepartment subDepartment = testEntityManager.persist(subDepartmentToSave);
//        System.out.println(subDepartment);
//        assertNotNull(subDepartment);
//
//    }

    @Test
    public void testfindByName() {
        SubDepartment subDepartmentToSearch = getSubDepartmentInfoToSave();
        Integer countName = subDepartmentRepository.findByName(subDepartmentToSearch.getName());
        System.out.println(countName);
        assertTrue(countName == 1);
    }

    @Test
    public void testfindByCode() {
        SubDepartment subDepartmentToSearch = getSubDepartmentInfoToSave();
        Integer countCode = subDepartmentRepository.findByCode(subDepartmentToSearch.getCode());
        System.out.println(countCode);
        assertTrue(countCode == 1);
    }

    @Test
    public void testfetchSubDepartments(){
       List<SubDepartment> subDepartment=subDepartmentRepository.fetchSubDepartments();
        assertTrue(subDepartment.size()>0);
    }

    @Test
    public void testfetchMinimalSubDepartmentData() {
        List<SubDepartmentResponseDTO> subDepartmentResponseDTOS = subDepartmentRepository.fetchMinimalSubDepartmentData();
        System.out.println(subDepartmentResponseDTOS);
        assertTrue(subDepartmentResponseDTOS.size()>0);
    }
}

