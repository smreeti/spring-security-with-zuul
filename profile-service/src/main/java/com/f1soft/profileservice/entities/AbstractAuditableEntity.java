package com.f1soft.profileservice.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditableEntity<U, ID> extends AbstractPersistableEntity<ID> implements Serializable {

    @CreatedDate
    LocalDate createdDate;

    @LastModifiedDate
    LocalDate lastModifiedDate;

    @CreatedBy
    @Column(name = "created_by")
    U createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    U lastModifiedBy;
}
