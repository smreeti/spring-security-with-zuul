package com.f1soft.departmentservice.unittest;


import com.f1soft.departmentservice.repository.DepartmentRepositoryCustom;
import com.f1soft.departmentservice.repository.DepartmentRepositoryCustomImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DepartmentCustomTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DepartmentRepositoryCustomImpl departmentRepositoryCustom;

    @Test
    public void test() {

        System.out.println(entityManager);
        departmentRepositoryCustom.refresh();
    }
}
