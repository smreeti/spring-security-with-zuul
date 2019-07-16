package com.f1soft.departmentservice.repository;


import com.f1soft.departmentservice.entities.Department;
import com.f1soft.departmentservice.responseDTO.requestDTO.DepartmentResponseDTO;
import com.f1soft.departmentservice.utils.DepartmentUtils;
import com.f1soft.departmentservice.utils.QueryCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class DepartmentRepositoryCustomImpl implements DepartmentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<DepartmentResponseDTO> fetchDepartmentWithMinimalData() {
        String sql= QueryCreator.createQueryToFetchDepartment();
        List<Object[]> objects=entityManager.createNativeQuery(sql).getResultList();
        List<DepartmentResponseDTO> departmentResponseDTO= DepartmentUtils.convertObjectToDepartmentResponseDTO.apply(objects);
        return departmentResponseDTO;
    }
}
