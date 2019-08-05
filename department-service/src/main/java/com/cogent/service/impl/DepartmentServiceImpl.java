package com.cogent.service.impl;

import com.cogent.dto.request.Department.DepartmentRequestDTO;
import com.cogent.dto.response.DepartmentResponseDTO;
import com.cogent.exceptionHandler.BadRequestDataException;
import com.cogent.exceptionHandler.DataAlreadyExistsException;
import com.cogent.exceptionHandler.DataNotFoundException;
import com.cogent.modal.Department;
import com.cogent.repository.DepartmentRepository;
import com.cogent.service.DepartmentService;
import com.cogent.utils.DepartmentUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.cogent.constants.ErrorMessageConstants.*;

/**
 * @author Sauravi
 */


@Service
@Slf4j
@Transactional
public class DepartmentServiceImpl implements DepartmentService {


    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void createDepartment(DepartmentRequestDTO departmentRequestDto) {
        if (departmentRequestDto == null) {
//            validateDepartmentName(departmentRequestDto.getDepartmentName());
            throw new BadRequestDataException(BAD_REQUEST);
//            validateDepartmentCode(departmentRequestDto.getCode());
        }
        for (int i = 0; i <= 50000; i++) {
            Department department = DepartmentUtils.convertdepartmentRequestDtoToDepartment.apply(departmentRequestDto);
            saveDepartment(department);
        }
    }

    @Override
    public Page<Department> fetchDepartmentDataWithpagination() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("departmentName"));
//        departmen long startTime = System.nanoTime()tRepository.findAll(pageable);
        long startTime = System.currentTimeMillis();
        departmentRepository.findAll(pageable);
        log.info("Execution took {}ms", (System.currentTimeMillis() - startTime));
        return departmentRepository.findAll(pageable);
    }

    @Override
    public void executeGridObjectListDemo() throws IOException {

        log.info("process started:::");

        long startTime = System.currentTimeMillis();

       String[] columns = {"id", "name", "code", "status"};

        List<Department> departmentList = departmentRepository.findDeps();

        log.info("Execution took {}ms", (System.currentTimeMillis() - startTime));

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("department");

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


        for (Department departments : departmentList) {
            Long id=departments.getId();
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(id);
            row.createCell(1).setCellValue(departments.getDepartmentName());
            row.createCell(2).setCellValue(departments.getCode());
            row.createCell(3).setCellValue(departments.getStatus().toString());

        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("/home/f1soft/Documents/cogent-workspace/spring-security-with-zuul/department-service/src/main/resources/department.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();

        log.info("Execution took {}ms", (System.currentTimeMillis() - startTime));
    }


    @Override
    public List<Department> fetchAllDepartment() {
        long startTime = System.currentTimeMillis();
        departmentRepository.fetchAllDepartment();
        log.info("Execution took {}ms", (System.currentTimeMillis() - startTime));
        return departmentRepository.fetchAllDepartment().orElseThrow(() ->
                new DataNotFoundException(DEPARTMENT_NOT_FOUND));
    }

    @Override
    public List<DepartmentResponseDTO> fetchMinimalDepartmentData() {
        return departmentRepository.fetchMinimalDepartmentData().orElseThrow(() ->
                new DataNotFoundException(DEPARTMENT_NOT_FOUND));
    }


    @Override
    public Department deleteDepartment(Long id) {
        Department departmentToDelete = findById(id);
        validateDepartmentInfo(departmentToDelete);
        Department departmentToSave = DepartmentUtils.convertDepartmentToDelete.apply(departmentToDelete);

        return saveDepartment(departmentToSave);
    }

    @Override
    public Department updateDepartment(DepartmentRequestDTO departmentRequestDto) {
        Department savedDepartment = findById(departmentRequestDto.getId());
        validateDepartmentInfo(savedDepartment);
        validateDepartmentName(departmentRequestDto.getDepartmentName());
        Department departmentToSave = DepartmentUtils.convertDepartmentToUpdate.apply(departmentRequestDto, savedDepartment);
        return saveDepartment(departmentToSave);

    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findByDepartmentId(id);
    }


    public void validateDepartmentName(String name) {
        if (departmentRepository.findByName(name) != null)
            throw new DataAlreadyExistsException(DEPARTMENT_ALREADY_EXISTS_WITH_NAME + name);
    }

    public void validateDepartmentCode(String code) {
        if (departmentRepository.findByCode(code) != null)
            throw new DataAlreadyExistsException(DEPARTMENT_ALREADY_EXISTS_WITH_CODE + code);
    }

    public void validateDepartmentInfo(Department department) {
        if (department == null)
            throw new DataNotFoundException(DEPARTMENT_NOT_FOUND);
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }


}
