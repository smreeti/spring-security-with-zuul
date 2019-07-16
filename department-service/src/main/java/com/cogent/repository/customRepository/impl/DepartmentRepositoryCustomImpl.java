package com.cogent.repository.customRepository.impl;


import com.cogent.DTO.responseDTO.DepartmentResponseDTO;
import com.cogent.repository.customRepository.DepartmentRepositoryCustom;
import com.cogent.utils.DepartmentUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static com.cogent.query.Department.createQueryTofetchMinimalDepartmentData;

@Service
@Transactional
public class DepartmentRepositoryCustomImpl implements DepartmentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<List<DepartmentResponseDTO>> fetchMinimalDepartmentData() {
        String sql = createQueryTofetchMinimalDepartmentData();
        List<Object[]> objects = entityManager.createNativeQuery(sql).getResultList();
        List<DepartmentResponseDTO> departmentResponseDTO = DepartmentUtils.convertObjectToDepartmentResponseDTO.apply(objects);
        return Optional.of(departmentResponseDTO);
    }
}
