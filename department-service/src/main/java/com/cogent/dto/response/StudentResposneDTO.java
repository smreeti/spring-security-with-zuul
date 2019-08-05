package com.cogent.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel

public class StudentResposneDTO {
    private Long id;

    private String name;

    private int age;

    private String address;

    private int grade;

    private Character gender;

    private String nationality;

    private String religion;

    private Date DOB;

    private String Phone;

    private Character status;

    private String fatherName;

    private String motherName;

    private String grandFatherName;

    private String schoolName;

    private String district;

    private String zone;

    private int ward;

    private Date dateOfJoin;

    private String bloodGroup;

    private String remarks;

}
