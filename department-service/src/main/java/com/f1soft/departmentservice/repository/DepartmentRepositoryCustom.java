package com.f1soft.departmentservice.repository;

import com.f1soft.departmentservice.responseDTO.requestDTO.DepartmentResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("repo1")
public interface DepartmentRepositoryCustom {
    Optional<List<DepartmentResponseDTO>> fetchMinimalDepartmentData();
}
