package com.cogent.service;

import com.cogent.dto.request.student.StudentRequestDTO;

import java.io.IOException;

public interface StudentService {
    void createstudent(StudentRequestDTO studentRequestDTO);
    void executeGridObjectListDemo() throws IOException;
}
