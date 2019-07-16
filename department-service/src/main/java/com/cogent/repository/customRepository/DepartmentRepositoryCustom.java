package com.cogent.repository.customRepository;

import com.cogent.DTO.responseDTO.DepartmentResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("departmentRepositoryCustom")
public interface DepartmentRepositoryCustom {
    Optional<List<DepartmentResponseDTO>> fetchMinimalDepartmentData();
}
