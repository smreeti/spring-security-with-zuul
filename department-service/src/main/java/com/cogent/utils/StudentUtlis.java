package com.cogent.utils;

import com.cogent.dto.request.student.StudentRequestDTO;
import com.cogent.modal.Student;

public class StudentUtlis {
        public static Student parseToSubDepartment(StudentRequestDTO studentRequestDTO) {
        return MapperUtility.map(studentRequestDTO, Student.class);
    }
}
