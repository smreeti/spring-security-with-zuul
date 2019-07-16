package com.f1soft.departmentservice.repositorytest;

import com.f1soft.departmentservice.AbstractDepartmentInfo;
import com.f1soft.departmentservice.entities.Department;
import com.f1soft.departmentservice.repository.DepartmentRepository;
import com.f1soft.departmentservice.responseDTO.requestDTO.DepartmentResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertNotNull;

/**
 * @author Sauravi
 */

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class DepartmentRepositoryTest extends AbstractDepartmentInfo {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void testDepartmentRepository(){
        testfetchAllDepartment();
        testfetchMinimalDepartmentData();
        testfindByName();
        testfindByCode();
        testfindByDepartmentId();
    }

    @Test
    public void testSaveDepartment() {
        Department departmentToSave = getDepartmentInfo();
        Department department = testEntityManager.persist(departmentToSave);
        System.out.println(department);
        assertThat(department).isNotNull();
    }

    @Test
    public void testfetchAllDepartment() {
        Optional<List<Department>> departmentList = departmentRepository.fetchAllDepartment();
        System.out.println(departmentList);
        assertNotNull(departmentList);
    }

    @Test
    public void testfetchMinimalDepartmentData(){
        Optional<List<DepartmentResponseDTO>> departmentResponseDTOS=departmentRepository.fetchMinimalDepartmentData();
        System.out.println(departmentResponseDTOS);
        assertNotNull(departmentResponseDTOS);
    }

    @Test
    public void testfindByName() {
        Department departmentSavedInDb = departmentRepository.findByName(getDepartmentInfo().getDepartmentName());


        System.out.println(departmentSavedInDb);
        assertThat(departmentSavedInDb.getDepartmentName()).isEqualTo(getDepartmentInfo().getDepartmentName());
    }

    @Test
    public void testfindByCode() {
        Department departmentSavedInDb = departmentRepository.findByCode(getDepartmentInfo().getCode());

        System.out.println(departmentSavedInDb);
        assertThat(departmentSavedInDb.getCode()).isEqualTo(getDepartmentInfo().getCode());
    }

    @Test
    public void testfindByDepartmentId() {
        Department departmentSavedInDb = departmentRepository.findByDepartmentId(3L);
        System.out.println(departmentSavedInDb);
        assertThat(departmentSavedInDb.getDepartmentName()).isEqualTo(getDepartmentInfo().getDepartmentName());
        assertThat(departmentSavedInDb.getCode()).isEqualTo(getDepartmentInfo().getCode());
    }



}
