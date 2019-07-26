package com.cogent.adminservice.repository.impl;

import com.cogent.adminservice.repository.AdminRepositoryCustom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author smriti on 7/21/19
 */
@Service
@Transactional
public class AdminRepositoryCustomImpl implements AdminRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

}
