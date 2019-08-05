package com.cogent.dto.request.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequestDTO {
    private Long id;

    private String name;

    private int age;

    private String address;

    private int grade;

    private Character gender;

    private String nationality;

    private String religion;

    private String DOB;

    private String phone;

    private Character status;

    private String fatherName;

    private String motherName;

    private String grandFatherName;

    private String schoolName;

    private String district;

    private String zone;

    private int ward;

    private String bloodGroup;

    private String remarks;


}
