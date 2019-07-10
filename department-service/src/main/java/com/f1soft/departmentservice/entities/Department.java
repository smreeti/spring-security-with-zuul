package com.f1soft.departmentservice.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "department")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "department_name" ,nullable = false, length = 50)
    private String departmentName;

    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Column(name = "status")
    private Character status;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "last_modified_date")
    private LocalDate lastModifiedDate;

    @Column(name="created_by_id")
    private Long createdById;

    @Column(name="last_modified_by_id")
    private Long modifiedById;

}
