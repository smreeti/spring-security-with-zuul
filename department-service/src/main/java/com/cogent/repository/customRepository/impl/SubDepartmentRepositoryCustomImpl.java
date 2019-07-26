package com.cogent.repository.customRepository.impl;

import com.cogent.dto.response.DepartmentResponseDTO;
import com.cogent.dto.response.SubDepartmentResponseDTO;
import com.cogent.modal.Department;
import com.cogent.modal.SubDepartment;
import com.cogent.repository.customRepository.SubDepartmentRepositoryCustom;
import com.cogent.utils.SubDepartmentUtlis;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.function.Supplier;

import static com.cogent.query.Department.createQueryTofetchMinimalDepartmentData;
import static com.cogent.query.SubDepartment.createQueryTofetchMinimalSubDepartmentData;

@Service
@Transactional
public class SubDepartmentRepositoryCustomImpl implements SubDepartmentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SubDepartmentResponseDTO> fetchMinimalSubDepartmentData() {
        List<Object[]> objects = entityManager.createNativeQuery(
                createQueryTofetchMinimalSubDepartmentData.get()).getResultList();

        List<SubDepartmentResponseDTO> subDepartmentResponseDTOS= SubDepartmentUtlis.parseObjectToSubDepartmentResponseDTO.apply(objects);
    return subDepartmentResponseDTOS;
    }

}
