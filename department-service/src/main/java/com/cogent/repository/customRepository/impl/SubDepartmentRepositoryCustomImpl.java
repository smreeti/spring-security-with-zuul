package com.cogent.repository.customRepository.impl;

import com.cogent.repository.customRepository.SubDepartmentRepositoryCustom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class SubDepartmentRepositoryCustomImpl implements SubDepartmentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

}
