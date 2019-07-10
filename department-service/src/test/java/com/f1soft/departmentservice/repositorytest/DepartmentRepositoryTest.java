package com.f1soft.departmentservice.repositorytest;

import com.f1soft.departmentservice.AbstractDepartmentInfo;
import com.f1soft.departmentservice.entities.Department;
import com.f1soft.departmentservice.repository.DepartmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class DepartmentRepositoryTest extends AbstractDepartmentInfo {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void testSaveDepartment() {
        Department departmentToSave = getDepartmentInfo();
        Department department = testEntityManager.persist(departmentToSave);
        assertThat(department).isEqualTo(departmentToSave);
    }


}
