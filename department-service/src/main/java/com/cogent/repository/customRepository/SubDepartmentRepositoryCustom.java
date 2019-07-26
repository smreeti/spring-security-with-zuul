package com.cogent.repository.customRepository;

import com.cogent.dto.response.DepartmentResponseDTO;
import com.cogent.dto.response.SubDepartmentResponseDTO;
import com.cogent.modal.SubDepartment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("subDepartmentRepositoryCustom")
public interface SubDepartmentRepositoryCustom {
    List<SubDepartmentResponseDTO> fetchMinimalSubDepartmentData();
}
