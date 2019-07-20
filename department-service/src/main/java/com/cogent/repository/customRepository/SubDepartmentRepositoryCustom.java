package com.cogent.repository.customRepository;

import com.cogent.modal.SubDepartment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("subDepartmentRepositoryCustom")
public interface SubDepartmentRepositoryCustom {
    void test();
}
