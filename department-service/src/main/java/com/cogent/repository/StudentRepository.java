package com.cogent.repository;

import com.cogent.modal.Department;
import com.cogent.modal.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository  extends JpaRepository<Student,Long> {

    @Query(value = "SELECT s FROM Student s WHERE s.status ='Y'")
    List<Student> findAllStudents();
}
