package com.cogent.repository.customRepository.impl;

import com.cogent.modal.Department;
import com.cogent.modal.SubDepartment;
import com.cogent.repository.customRepository.SubDepartmentRepositoryCustom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class SubDepartmentRepositoryCustomImpl implements SubDepartmentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void test() {
        javax.persistence.Query query = entityManager.createNativeQuery
                ("SELECT * FROM sub_department WHERE name='Billing' AND status='Y'");

//
//        List<SubDepartment> departments = (List<SubDepartment>) entityManager.createNativeQuery(
//                ("SELECT * FROM sub_department WHERE name='Billing' AND status='Y'"),
//                SubDepartment.class);


        List<SubDepartment> subDepartment = (List<SubDepartment>) entityManager.createNativeQuery(
                "SELECT * FROM sub_department WHERE name='Billing' AND status='Y'", SubDepartment.class).getResultList();

        System.out.println(subDepartment);


    }

}
