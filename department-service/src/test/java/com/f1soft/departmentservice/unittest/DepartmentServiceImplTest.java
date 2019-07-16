package com.f1soft.departmentservice.unittest;

import com.f1soft.departmentservice.entities.Department;
import com.f1soft.departmentservice.exception.BadRequestDataException;
import com.f1soft.departmentservice.exception.DataAlreadyExistsException;
import com.f1soft.departmentservice.exception.DataNotFoundException;
import com.f1soft.departmentservice.exception.NoChangeFoundException;
import com.f1soft.departmentservice.repository.DepartmentRepository;
import com.f1soft.departmentservice.repository.DepartmentRepositoryCustom;
import com.f1soft.departmentservice.requestDTO.DepartmentSetupDTO;
import com.f1soft.departmentservice.requestDTO.UpdatedDepartmentDTO;
import com.f1soft.departmentservice.responseDTO.requestDTO.DepartmentResponseDTO;
import com.f1soft.departmentservice.service.serviceimpl.DepartmentServiceImpl;
import com.f1soft.departmentservice.utils.DepartmentUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.f1soft.departmentservice.utils.DepartmentUtils.convertDepartmentSetupToDepartment;
import static com.f1soft.departmentservice.utils.DepartmentUtils.convertDepartmentToDelete;
import static com.f1soft.departmentservice.utils.DepartmentUtils.convertDepartmentToUpdate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
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

    private DepartmentSetupDTO departmentSetupDTO;

    private Department savedDepartment;

    private DepartmentResponseDTO limitedData;

    private UpdatedDepartmentDTO updatedDepartmentDTO;

    @Before
    public void setUp() {
        departmentService = new DepartmentServiceImpl(departmentRepository);
        departmentSetupDTO = DepartmentSetupDTO.builder()
                .departmentName("Surgical")
                .code("SRG")
                .status('Y')
                .build();

        updatedDepartmentDTO=new UpdatedDepartmentDTO();
        updatedDepartmentDTO.setId(1L);
        updatedDepartmentDTO.setDepartmentName("Sugery");
        updatedDepartmentDTO.setCode("SRG");
        updatedDepartmentDTO.setStatus('Y');


        savedDepartment = new Department();
        savedDepartment.setId(1L);
        savedDepartment.setDepartmentName("Surgical");
        savedDepartment.setCode("SRG");
        savedDepartment.setStatus('Y');
        savedDepartment.setCreatedDate(null);
        savedDepartment.setCreatedById(1L);
        savedDepartment.setLastModifiedDate(null);
        savedDepartment.setModifiedById(1L);


        limitedData=new DepartmentResponseDTO();
        limitedData.setId(1L);
        limitedData.setDepartmentName("Surgical");
        limitedData.setCode("SRG");
        limitedData.setStatus('Y');
    }

    @Test
    public void createDepartment() {
        createDepartment_ShouldReturnNameExists();
        createDepartment_ShouldReturnCodeExists();
        createDepartment_ShouldSaveData();
        createDepartment_ShouldReturnBadRequest();
    }

    @Test
    public void fetchAllDepartment(){
        fetchAllDepartment_ShouldReturnEmptyList();
        fetchAllDepartment_ShouldReturnDepartments();
    }

    @Test
    public void fetchMinimalDepartmentData(){
        fetchMinimalDepartmentData_ShouldReturnEmptyList();
        fetchMinimalDepartmentData_ShouldReturnDepartment();
    }

    @Test
    public void deleteDeparment(){
        deleteDepartment_ShouldReturnDataNotFound();
        deleteDepartment_ShouldDeleteData();
    }

    @Test
    public void updateDepartment(){
        updateDepartment_ShouldReturnDataNotFound();
        updateDepartment_ShouldUpdateDepartment();
    }


    @Test
    public void createDepartment_ShouldReturnNameExists() {

        thrown.expect(DataAlreadyExistsException.class);

        given(departmentRepository.findByName(departmentSetupDTO.getDepartmentName())).willReturn(savedDepartment);

        departmentService.createDepartment(departmentSetupDTO);

    }

    @Test
    public void createDepartment_ShouldReturnCodeExists() {
        thrown.expect(DataAlreadyExistsException.class);

        given(departmentRepository.findByName(departmentSetupDTO.getDepartmentName())).willReturn(null);
        given(departmentRepository.findByCode(departmentSetupDTO.getCode())).willReturn(savedDepartment);

        departmentService.createDepartment(departmentSetupDTO);
    }

    @Test
    public void createDepartment_ShouldSaveData() {
        Department department = convertDepartmentSetupToDepartment.apply(departmentSetupDTO);

        given(departmentRepository.findByName(departmentSetupDTO.getDepartmentName())).willReturn(null);
        given(departmentRepository.findByCode(departmentSetupDTO.getCode())).willReturn(null);
        given(departmentRepository.save(department)).willReturn(savedDepartment);

        assertThat(departmentService.createDepartment(departmentSetupDTO)).isEqualTo(savedDepartment);
        verify(departmentRepository).save(department);
    }

    @Test
    public void createDepartment_ShouldReturnBadRequest() {
        thrown.expect(BadRequestDataException.class);

        departmentService.createDepartment(null);
    }

    @Test
    public void fetchAllDepartment_ShouldReturnEmptyList(){
        thrown.expect(DataNotFoundException.class);

        given(departmentRepository.fetchAllDepartment()).willReturn(Optional.ofNullable(null));

        departmentService.fetchAllDepartment();
    }

    @Test
    public void fetchAllDepartment_ShouldReturnDepartments(){
        List<Department> departmentList=new ArrayList<>();
        departmentList.add(savedDepartment);

        given(departmentRepository.fetchAllDepartment()).willReturn(Optional.of(departmentList));

        assertThat(departmentService.fetchAllDepartment()).isEqualTo(departmentList);
        assertNotNull(departmentService.fetchAllDepartment());
    }

    @Test
    public void fetchMinimalDepartmentData_ShouldReturnEmptyList(){
        thrown.expect(DataNotFoundException.class);

        given(departmentRepository.fetchMinimalDepartmentData()).willReturn(Optional.ofNullable(null));

        departmentService.fetchMinimalDepartmentData();

    }

    @Test
    public void fetchMinimalDepartmentData_ShouldReturnDepartment(){
        List<DepartmentResponseDTO> departmentList=new ArrayList<>();
        departmentList.add(limitedData);

        given(departmentRepository.fetchMinimalDepartmentData()).willReturn(Optional.of(departmentList));

        assertThat(departmentService.fetchMinimalDepartmentData()).isEqualTo(departmentList);
        assertNotNull(departmentService.fetchMinimalDepartmentData());
    }




    @Test
    public void deleteDepartment_ShouldReturnDataNotFound(){
        thrown.expect(DataNotFoundException.class);

        given(departmentRepository.findByDepartmentId(1L)).willReturn(null);

        departmentService.deleteDepartment(1L);
    }

    @Test
    public void deleteDepartment_ShouldDeleteData(){
        Department departmentToSave=convertDepartmentToDelete.apply(savedDepartment);

        given(departmentRepository.findByDepartmentId(1L)).willReturn(savedDepartment);
        given(departmentRepository.save(departmentToSave)).willReturn(departmentToSave);

        assertThat(departmentService.deleteDepartment(savedDepartment.getId())).isEqualTo(departmentToSave);
        verify(departmentRepository).save(departmentToSave);
    }

    @Test
    public void updateDepartment_ShouldReturnDataNotFound(){
        thrown.expect(DataNotFoundException.class);

        given(departmentRepository.findByDepartmentId(updatedDepartmentDTO.getId())).willReturn(null);

        departmentService.updateDepartment(updatedDepartmentDTO);
    }



    @Test
    public void updateDepartment_ShouldUpdateDepartment(){
        Department departmentToSave=convertDepartmentToUpdate.apply(updatedDepartmentDTO,savedDepartment);
        given(departmentRepository.findByDepartmentId(updatedDepartmentDTO.getId())).willReturn(savedDepartment);
        given(departmentRepository.save(departmentToSave)).willReturn(departmentToSave);

        assertThat(departmentService.updateDepartment(updatedDepartmentDTO)).isEqualTo(departmentToSave);
        verify(departmentRepository).save(departmentToSave);

    }


}
