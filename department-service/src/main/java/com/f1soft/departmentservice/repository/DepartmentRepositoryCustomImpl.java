package com.f1soft.departmentservice.repository;


import com.f1soft.departmentservice.entities.Department;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class DepartmentRepositoryCustomImpl implements DepartmentRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Department findByName(String name) {
        return null;
    }

    @Override
    public void refresh() {
        System.out.println(entityManager);
        System.out.println("ok");
    }
}
