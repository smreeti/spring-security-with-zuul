//package com.cogent.profileservice;
//
//import com.cogent.testcrud.entity.User;
//import com.cogent.testcrud.exceptionHandler.DataAlreadyExistsException;
//import com.cogent.testcrud.exceptionHandler.DataNotFoundException;
//import com.cogent.testcrud.exceptionHandler.NoChangefoundException;
//import com.cogent.testcrud.repository.UserRepository;
//import com.cogent.testcrud.service.impl.UserServiceImpl;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.verify;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UserServiceImplTest {
//
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    @Mock
//    UserRepository userRepository;
//
//    private UserServiceImpl userService;
//
//    @Before
//    public void setUp() {
//        userService = new UserServiceImpl(userRepository);
//    }
//
//
//    @Test
//    public void createUser() {
//        Create_ShouldReturnDataAlreadyExists();
//        Create_ShouldCreateUser();
//    }
//
//    @Test
//    public void Create_ShouldReturnDataAlreadyExists() {
//        User databaseUser = User.builder().id(1L).name("sauravi").address("baluwatar").build();
//        User createData = User.builder().name("sauravi").address("baluwatar").build();
//
//        thrown.expect(DataAlreadyExistsException.class);
//        thrown.expectMessage(createData.getName() + " " + "Already exists");
//
//        given(userRepository.findByName(createData.getName())).willReturn(databaseUser);
//
//        userService.createUser(databaseUser);
//    }
//
//    @Test
//    public void Create_ShouldCreateUser() {
//        User createData = User.builder().name("sauravi").address("baluwatar").build();
//
//        given(userRepository.findByName(createData.getName())).willReturn(null);
//        given(userRepository.save(createData)).willReturn(createData);
//
//        assertThat(userService.createUser(createData)).isEqualTo(createData);
//        verify(userRepository).save(createData);
//
//    }
//
//    @Test
//    public void Update() {
//        Update_ShouldReturnDataAlreadyExists();
//        Update_ShouldReturnNoChangeFound();
//        Update_ShouldUpdateUser();
//    }
//
//    @Test
//    public void Update_ShouldReturnDataAlreadyExists() {
//        User updatedData = User.builder().id(1L).name("sauravi").address("baluwatar").build();
//
//        thrown.expect(DataNotFoundException.class);
//        thrown.expectMessage("Data Not Found");
//
//        given(userRepository.findUserByID(updatedData.getId())).willReturn(null);
//
//        userService.updateuser(updatedData);
//    }
//
//    @Test
//    public void Update_ShouldReturnNoChangeFound() {
//        User databaseUser = User.builder().id(1L).name("sauravi").address("baluwatar").build();
//        User updatedData = User.builder().id(1L).name("sauravi").address("baluwatar").build();
//
//        thrown.expect(NoChangefoundException.class);
//        thrown.expectMessage("No Changes Found");
//
//        given(userRepository.findUserByID(updatedData.getId())).willReturn(databaseUser);
//        given(userRepository.findUser(updatedData.getId(), updatedData.getName(), updatedData.getAddress())).willReturn(databaseUser);
//
//        userService.updateuser(updatedData);
//    }
//
//    @Test
//    public void Update_ShouldUpdateUser() {
//        User databaseUser = User.builder().id(1L).name("sauravi").address("baluwatar").build();
//        User updatedData = User.builder().id(1L).name("sadikshya").address("baluwatar").build();
//
//        given(userRepository.findUser(updatedData.getId(), updatedData.getName(), updatedData.getAddress())).willReturn(null);
//        given(userRepository.findUserByID(updatedData.getId())).willReturn(databaseUser);
//
//        assertThat(userService.updateuser(updatedData)).isEqualTo(null);
//
//    }
//
//
//}
