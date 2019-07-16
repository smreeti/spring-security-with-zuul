package com.f1soft.departmentservice.repository;


import com.f1soft.departmentservice.responseDTO.requestDTO.DepartmentResponseDTO;
import com.f1soft.departmentservice.utils.DepartmentUtils;
import com.f1soft.departmentservice.utils.QueryCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartmentRepositoryCustomImpl implements DepartmentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<List<DepartmentResponseDTO>> fetchMinimalDepartmentData() {
        String sql= QueryCreator.createQueryTofetchMinimalDepartmentData();
        List<Object[]> objects=entityManager.createNativeQuery(sql).getResultList();
        List<DepartmentResponseDTO> departmentResponseDTO= DepartmentUtils.convertObjectToDepartmentResponseDTO.apply(objects);
        return Optional.of(departmentResponseDTO);
    }
}
