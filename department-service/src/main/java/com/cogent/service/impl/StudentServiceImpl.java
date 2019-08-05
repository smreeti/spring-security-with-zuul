package com.cogent.service.impl;

import com.cogent.dto.request.student.StudentRequestDTO;
import com.cogent.exceptionHandler.BadRequestDataException;
import com.cogent.modal.Department;
import com.cogent.modal.Student;
import com.cogent.repository.StudentRepository;
import com.cogent.service.StudentService;
import com.cogent.utils.DepartmentUtils;
import com.cogent.utils.StudentUtlis;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static com.cogent.constants.ErrorMessageConstants.BAD_REQUEST;

@Service
@Slf4j
@Transactional
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void createstudent(StudentRequestDTO studentRequestDTO) {
        log.info("process started:::");

        long startTime = System.currentTimeMillis();
        if (studentRequestDTO == null) {
//            validateDepartmentName(departmentRequestDto.getDepartmentName());
            throw new BadRequestDataException(BAD_REQUEST);
//            validateDepartmentCode(departmentRequestDto.getCode());
        }
        for (int i = 0; i <= 100000; i++) {

            Student student= StudentUtlis.parseToSubDepartment(studentRequestDTO);
            saveDepartment(student);
        }
        log.info("Execution took {}ms", (System.currentTimeMillis() - startTime));
    }

    @Override
    public void executeGridObjectListDemo() throws IOException {
        log.info("process started:::");

        long startTime = System.currentTimeMillis();

        String[] columns = {"id", "name", "age", "address","grade","gender","nationality","religion","DOB","phone","status",
                "fatherName","motherName","grandFatherName","schoolName","district","zone","ward","bloodGroup","remarks"};

        List<Student> studentList=studentRepository.findAllStudents();


        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("student");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 25);
        headerFont.setColor(IndexedColors.RED.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Other rows and cells with contacts data
        int rowNum = 1;

        Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("${student.id}");
            row.createCell(1).setCellValue("${student.name}");
            row.createCell(2).setCellValue("${student.age}");
            row.createCell(3).setCellValue("${student.address}");
            row.createCell(4).setCellValue("${student.grade}");
            row.createCell(5).setCellValue("${student.gender}");
            row.createCell(6).setCellValue("${student.nationality}");
            row.createCell(7).setCellValue("${student.religion}");
            row.createCell(8).setCellValue("${student.DOB}");
            row.createCell(9).setCellValue("${student.phone}");
            row.createCell(10).setCellValue("${student.status}");
            row.createCell(11).setCellValue("${student.fatherName}");
            row.createCell(12).setCellValue("${student.motherName}");
            row.createCell(13).setCellValue("${student.grandFatherName}");
            row.createCell(14).setCellValue( "${student.schoolName}");
            row.createCell(15).setCellValue("${student.district}");
            row.createCell(16).setCellValue("${student.zone}");
            row.createCell(17).setCellValue("${student.ward}");
            row.createCell(18).setCellValue("${student.bloodGroup}");
            row.createCell(19).setCellValue("${student.remarks");


//        for (Student students : studentList) {
//            Long id=students.getId();
//            Row row = sheet.createRow(rowNum++);
//            row.createCell(0).setCellValue(id);
//            row.createCell(1).setCellValue(students.getName());
//            row.createCell(2).setCellValue(students.getAge());
//            row.createCell(3).setCellValue(students.getAddress());
//            row.createCell(4).setCellValue(students.getGrade());
//            row.createCell(5).setCellValue(students.getGender());
//            row.createCell(6).setCellValue(students.getNationality());
//            row.createCell(7).setCellValue(students.getReligion());
//            row.createCell(8).setCellValue(students.getDOB());
//            row.createCell(9).setCellValue(students.getPhone());
//            row.createCell(10).setCellValue(students.getStatus());
//            row.createCell(11).setCellValue(students.getFatherName());
//            row.createCell(12).setCellValue(students.getMotherName());
//            row.createCell(13).setCellValue(students.getGrandFatherName());
//            row.createCell(14).setCellValue(students.getSchoolName());
//            row.createCell(15).setCellValue(students.getDistrict());
//            row.createCell(16).setCellValue(students.getZone());
//            row.createCell(17).setCellValue(students.getWard());
//            row.createCell(18).setCellValue(students.getBloodGroup());
//            row.createCell(19).setCellValue(students.getRemarks());
//        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("/home/f1soft/Documents/cogent-workspace/spring-security-with-zuul/department-service/src/main/resources/student.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();

        log.info("Execution took {}ms", (System.currentTimeMillis() - startTime));
    }

    public void createExcel(){

    }






    public Student saveDepartment(Student student) {
        return studentRepository.save(student);
    }




}
