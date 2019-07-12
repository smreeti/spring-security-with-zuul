package com.f1soft.departmentservice.repository;

import com.f1soft.departmentservice.entities.Department;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("repo1")
public interface DepartmentRepositoryCustom {
    void refresh();
}
