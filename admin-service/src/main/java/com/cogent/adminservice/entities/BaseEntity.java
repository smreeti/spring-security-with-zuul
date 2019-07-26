package com.cogent.adminservice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    @Column(name = "created_date")
    @Temporal(value = TemporalType.DATE)
    private Date createdDate;

    @Column(name = "last_modified_date")
    @Temporal(value = TemporalType.DATE)
    private Date lastModifiedDate;

}
