package com.f1soft.departmentservice.unittest;

import com.f1soft.departmentservice.entities.Department;
import com.f1soft.departmentservice.exception.BadRequestDataException;
import com.f1soft.departmentservice.exception.DataAlreadyExistsException;
import com.f1soft.departmentservice.repository.DepartmentRepository;
import com.f1soft.departmentservice.requestDTO.DepartmentSetupDTO;
import com.f1soft.departmentservice.service.serviceimpl.DepartmentServiceImpl;
import com.f1soft.departmentservice.utils.DepartmentUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    DepartmentRepository departmentRepository;

    private DepartmentServiceImpl departmentService;

    private DepartmentSetupDTO departmentSetupDTO;

    private Department department;

    private Department savedDepartment;

    @Before
    public void setUp() {
        departmentService = new DepartmentServiceImpl(departmentRepository);
        departmentSetupDTO = DepartmentSetupDTO.builder()
                .departmentName("Surgical")
                .code("SRG")
                .status('Y')
                .build();

        department = new Department();
        department.setId(1L);
        department.setDepartmentName("Surgical");
        department.setCode("SRG");
        department.setStatus('Y');

        savedDepartment = new Department();
        savedDepartment.setId(1L);
        savedDepartment.setDepartmentName("Surgical");
        savedDepartment.setCode("SRG");
        savedDepartment.setStatus('Y');

    }

    @Test
    public void addDepartment() {
        addDepartment_ShouldReturnNameExists();
        addDepartment_ShouldReturnCodeExists();
        addDepartment_ShouldSaveData();
        addDepartment_ShhouldReturnBadRequest();
    }


    @Test
    public void addDepartment_ShouldReturnNameExists() {

        thrown.expect(DataAlreadyExistsException.class);
        thrown.expectMessage("DEPARTMENT WITH NAME :" + " " + departmentSetupDTO.getDepartmentName() + " " + " ALREADY EXISTS");

        given(departmentRepository.findByName(departmentSetupDTO.getDepartmentName())).willReturn(department);

        departmentService.addDepartment(departmentSetupDTO);

    }

    @Test
    public void addDepartment_ShouldReturnCodeExists() {
        thrown.expect(DataAlreadyExistsException.class);
        thrown.expectMessage("DEPARTMENT WITH CODE :" + " " + departmentSetupDTO.getCode() + " " + " ALREADY EXISTS");

        given(departmentRepository.findByName(departmentSetupDTO.getDepartmentName())).willReturn(null);
        given(departmentRepository.findByCode(departmentSetupDTO.getCode())).willReturn(department);

        departmentService.addDepartment(departmentSetupDTO);
    }

    @Test
    public void addDepartment_ShouldSaveData() {
        Department department = DepartmentUtils.convertDepartmentSetupToDepartment(departmentSetupDTO);

        given(departmentRepository.findByName(departmentSetupDTO.getDepartmentName())).willReturn(null);
        given(departmentRepository.findByCode(departmentSetupDTO.getCode())).willReturn(null);
        given(departmentRepository.save(department)).willReturn(savedDepartment);

        assertThat(departmentService.addDepartment(departmentSetupDTO)).isEqualTo(savedDepartment);
        verify(departmentRepository, times(2)).save(department);
    }

    @Test
    public void addDepartment_ShhouldReturnBadRequest() {
        thrown.expect(BadRequestDataException.class);
        thrown.expectMessage("Bad Request.");

        departmentService.addDepartment(null);

    }


}
