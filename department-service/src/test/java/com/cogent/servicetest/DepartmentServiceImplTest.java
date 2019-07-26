package com.cogent.servicetest;


import com.cogent.dto.request.Department.DepartmentRequestDTO;
import com.cogent.dto.response.DepartmentResponseDTO;
import com.cogent.exceptionHandler.BadRequestDataException;
import com.cogent.exceptionHandler.DataAlreadyExistsException;
import com.cogent.exceptionHandler.DataNotFoundException;
import com.cogent.modal.Department;
import com.cogent.repository.DepartmentRepository;

import com.cogent.service.impl.DepartmentServiceImpl;
import com.cogent.utils.DepartmentUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * @author Sauravi
 */

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private DepartmentRequestDTO departmentRequestDto;

    private DepartmentRequestDTO updatedDepartmentRequestDto;

    private Department savedDepartment;

    private DepartmentResponseDTO limitedData;


    @Before
    public void setUp() {
        departmentService = new DepartmentServiceImpl(departmentRepository);
        departmentRequestDto = DepartmentRequestDTO.builder()
                .departmentName("Surgical")
                .code("SRG")
                .status('Y')
                .build();

        updatedDepartmentRequestDto= DepartmentRequestDTO.builder()
                .id(1L)
                .departmentName("Surgical")
                .code("SRG")
                .status('Y')
                .build();


        savedDepartment = new Department();
        savedDepartment.setId(1L);
        savedDepartment.setDepartmentName("Surgical");
        savedDepartment.setCode("SRG");
        savedDepartment.setStatus('Y');
        savedDepartment.setCreatedDate(null);
        savedDepartment.setCreatedById(1L);
        savedDepartment.setLastModifiedDate(null);
        savedDepartment.setModifiedById(1L);


        limitedData = new DepartmentResponseDTO();
        limitedData.setId(1L);
        limitedData.setDepartmentName("Surgical");
        limitedData.setCode("SRG");
        limitedData.setStatus('Y');
    }

    @Test
    public void createDepartment() {
        createDepartment_ShouldReturnNameExists();
        createDepartment_ShouldReturnCodeExists();
        createDepartment_ShouldCreateData();
        createDepartment_ShouldReturnBadRequest();
    }

    @Test
    public void fetchAllDepartment() {
        fetchAllDepartment_ShouldReturnEmptyList();
        fetchAllDepartment_ShouldReturnDepartments();
    }

    @Test
    public void fetchMinimalDepartmentData() {
        fetchMinimalDepartmentData_ShouldReturnEmptyList();
        fetchMinimalDepartmentData_ShouldReturnDepartment();
    }

    @Test
    public void deleteDeparment() {
        deleteDepartment_ShouldReturnDataNotFound();
        deleteDepartment_ShouldDeleteData();
    }

    @Test
    public void updateDepartment() {
        updateDepartment_ShouldReturnDataNotFound();
        updateDepartment_ShouldUpdateDepartment();
    }


    @Test
    public void createDepartment_ShouldReturnNameExists() {

        thrown.expect(DataAlreadyExistsException.class);

        given(departmentRepository.findByName(departmentRequestDto.getDepartmentName())).willReturn(savedDepartment);

        departmentService.createDepartment(departmentRequestDto);

    }

    @Test
    public void createDepartment_ShouldReturnCodeExists() {
        thrown.expect(DataAlreadyExistsException.class);

        given(departmentRepository.findByName(departmentRequestDto.getDepartmentName())).willReturn(null);
        given(departmentRepository.findByCode(departmentRequestDto.getCode())).willReturn(savedDepartment);

        departmentService.createDepartment(departmentRequestDto);
    }

    @Test
    public void createDepartment_ShouldCreateData() {
//        Department department = DepartmentUtils.convertdepartmentRequestDtoToDepartment.apply(departmentRequestDto);

        given(departmentRepository.findByName(departmentRequestDto.getDepartmentName())).willReturn(null);
        given(departmentRepository.findByCode(departmentRequestDto.getCode())).willReturn(null);
        given(departmentRepository.save(any(Department.class))).willReturn(savedDepartment);

        assertThat(departmentService.createDepartment(departmentRequestDto)).isEqualTo(Optional.of(savedDepartment));
        verify(departmentRepository).save(any(Department.class));
    }

    @Test
    public void createDepartment_ShouldReturnBadRequest() {
        thrown.expect(BadRequestDataException.class);

        departmentService.createDepartment(null);
    }

    @Test
    public void fetchAllDepartment_ShouldReturnEmptyList() {
        thrown.expect(DataNotFoundException.class);

        given(departmentRepository.fetchAllDepartment()).willReturn(Optional.ofNullable(null));

        departmentService.fetchAllDepartment();
    }

    @Test
    public void fetchAllDepartment_ShouldReturnDepartments() {
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(savedDepartment);

        given(departmentRepository.fetchAllDepartment()).willReturn(Optional.of(departmentList));

        assertThat(departmentService.fetchAllDepartment()).isEqualTo(departmentList);
        assertNotNull(departmentService.fetchAllDepartment());
    }

    @Test
    public void fetchMinimalDepartmentData_ShouldReturnEmptyList() {
        thrown.expect(DataNotFoundException.class);

        given(departmentRepository.fetchMinimalDepartmentData()).willReturn(Optional.ofNullable(null));

        departmentService.fetchMinimalDepartmentData();

    }

    @Test
    public void fetchMinimalDepartmentData_ShouldReturnDepartment() {
        List<DepartmentResponseDTO> departmentList = new ArrayList<>();
        departmentList.add(limitedData);

        given(departmentRepository.fetchMinimalDepartmentData()).willReturn(Optional.of(departmentList));

        assertThat(departmentService.fetchMinimalDepartmentData()).isEqualTo(departmentList);
        assertNotNull(departmentService.fetchMinimalDepartmentData());
    }


    @Test
    public void deleteDepartment_ShouldReturnDataNotFound() {
        thrown.expect(DataNotFoundException.class);

        given(departmentRepository.findByDepartmentId(1L)).willReturn(null);

        departmentService.deleteDepartment(1L);
    }

    @Test
    public void deleteDepartment_ShouldDeleteData() {
        Department departmentToSave = DepartmentUtils.convertDepartmentToDelete.apply(savedDepartment);

        given(departmentRepository.findByDepartmentId(1L)).willReturn(savedDepartment);
        given(departmentRepository.save(any(Department.class))).willReturn(departmentToSave);

        assertThat(departmentService.deleteDepartment(savedDepartment.getId())).isEqualTo(departmentToSave);
        verify(departmentRepository).save(any(Department.class));
    }

    @Test
    public void updateDepartment_ShouldReturnDataNotFound() {
        thrown.expect(DataNotFoundException.class);

        given(departmentRepository.findByDepartmentId(updatedDepartmentRequestDto.getId())).willReturn(null);

        departmentService.updateDepartment(updatedDepartmentRequestDto);
    }

    @Test
    public void updateDepartment_ShouldReturnNameAlreadyExists() {
        thrown.expect(DataAlreadyExistsException.class);

        given(departmentRepository.findByDepartmentId(updatedDepartmentRequestDto.getId())).willReturn(savedDepartment);
        given(departmentRepository.findByName(updatedDepartmentRequestDto.getDepartmentName())).willReturn(savedDepartment);

        departmentService.updateDepartment(updatedDepartmentRequestDto);
    }


    @Test
    public void updateDepartment_ShouldUpdateDepartment() {
        Department departmentToSave = DepartmentUtils.convertDepartmentToUpdate.apply(updatedDepartmentRequestDto, savedDepartment);
        given(departmentRepository.findByDepartmentId(updatedDepartmentRequestDto.getId())).willReturn(savedDepartment);
        given(departmentRepository.findByName(updatedDepartmentRequestDto.getDepartmentName())).willReturn(null);
        given(departmentRepository.save(any(Department.class))).willReturn(departmentToSave);

        assertThat(departmentService.updateDepartment(updatedDepartmentRequestDto)).isEqualTo(departmentToSave);
        verify(departmentRepository).save(any(Department.class));

    }


}
