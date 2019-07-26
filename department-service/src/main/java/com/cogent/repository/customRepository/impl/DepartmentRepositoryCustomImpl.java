package com.cogent.repository.customRepository.impl;


import com.cogent.dto.response.DepartmentResponseDTO;
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
        List<Object[]> objects = entityManager.createNativeQuery(
                createQueryTofetchMinimalDepartmentData.get()).getResultList();
        List<DepartmentResponseDTO> departmentResponseDTO =
                DepartmentUtils.convertObjectToDepartmentResponseDTO.apply(objects);
        return Optional.of(departmentResponseDTO);
    }
}
