package com.f1soft.profileservice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@MappedSuperclass
public abstract  class AbstractPersistableEntity<ID> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ID id;
}
