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
import javax.persistence.Query;
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

        Query query=entityManager.createNativeQuery(createQueryTofetchMinimalSubDepartmentData.get());
        query.setFirstResult(0);
        query.setMaxResults(50);
        List<Object[]> objects = query.getResultList();

        List<SubDepartmentResponseDTO> subDepartmentResponseDTOS= SubDepartmentUtlis.parseObjectToSubDepartmentResponseDTO.apply(objects);
    return subDepartmentResponseDTOS;
    }

    @Override
    public List<SubDepartmentResponseDTO> fetchSubDepartmentData() {

        String optimizedSQL = "SELECT s FROM SubDepartment s WHERE s.status = :status";
//        String sql= "SELECT sd FROM SubDepartment sd WHERE sd.status='Y'";
        Query query=entityManager.createQuery(optimizedSQL);
        query.setParameter("status", 'Y');
        query.setFirstResult(0);
        query.setMaxResults(50);
        List<SubDepartment> objects = query.getResultList();

        List<SubDepartmentResponseDTO> subDepartmentResponseDTOS= SubDepartmentUtlis.parsePaginationToSubDepartmentResponseDTO.apply(objects);
        return subDepartmentResponseDTOS;
    }


}
