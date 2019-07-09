//package com.f1soft.testcrud.unittest;
//
//import com.f1soft.testcrud.entity.User;
//import com.f1soft.testcrud.exception.DataAlreadyExistsException;
//import com.f1soft.testcrud.exception.DataNotFoundException;
//import com.f1soft.testcrud.exception.NoChangefoundException;
//import com.f1soft.testcrud.repository.UserRepository;
//import com.f1soft.testcrud.service.serviceimpl.UserServiceImpl;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static junit.framework.TestCase.assertFalse;
//import static junit.framework.TestCase.assertTrue;
//import static org.assertj.core.api.Java6Assertions.assertThat;
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
//        create_ShouldReturnDataAlreadyExists();
//        create_ShouldCreateUser();
//    }
//
//    @Test
//    public void updateUser() {
//        update_ShouldReturnDataAlreadyExists();
//        update_ShouldReturnNoChangeFound();
//        update_ShouldReturnNameAlreadyExists();
//        update_ShouldUpdateUserName();
//        update_ShouldUpdateUserAddress();
//
//    }
//
////    @Test
////    public void fetchAllUsers(){
////        fetchAllUsers_ShouldReturnEmptyList();
////        fetchAllUsers_ShouldReturnList();
////    }
//
//    @Test
//    public void deleteUserById(){
//        deleteUserById_ShouldReturnDataNotFound();
//        deleteUserById_ShouldDeleteData();
//    }
//
//
//
//    @Test
//    public void create_ShouldReturnDataAlreadyExists() {
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
//    public void create_ShouldCreateUser() {
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
//
//    @Test
//    public void update_ShouldReturnDataAlreadyExists() {
//        User updatedData = User.builder().id(1L).name("sauravi").address("baluwatar").build();
//
//        thrown.expect(DataNotFoundException.class);
//        thrown.expectMessage("Data Not Found");
//
//        given(userRepository.findUserByID(updatedData.getId())).willReturn(null);
//
//        userService.updateUser(updatedData);
//    }
//
//    @Test
//    public void update_ShouldReturnNoChangeFound() {
//        User databaseUser = User.builder().id(1L).name("sauravi").address("baluwatar").build();
//        User updatedData = User.builder().id(1L).name("sauravi").address("baluwatar").build();
//
//        thrown.expect(NoChangefoundException.class);
//        thrown.expectMessage("No Changes Found");
//
//        given(userRepository.findUserByID(updatedData.getId())).willReturn(databaseUser);
//        given(userRepository.findUser(updatedData.getId(), updatedData.getName(), updatedData.getAddress())).willReturn(databaseUser);
//
//        userService.updateUser(updatedData);
//    }
//
//    @Test
//    public void update_ShouldReturnNameAlreadyExists() {
//        User databaseUser = User.builder().id(1L).name("sadikshya").address("patan").build();
//        User updatedData = User.builder().id(1L).name("sadikshya").address("baluwatar").build();
//
//        thrown.expect(DataAlreadyExistsException.class);
//        thrown.expectMessage(updatedData.getName()+" "+"Already exists");
//
//        given(userRepository.findUser(updatedData.getId(), updatedData.getName(), updatedData.getAddress())).willReturn(null);
//        given(userRepository.findUserByID(updatedData.getId())).willReturn(databaseUser);
//        given(userRepository.findByName(updatedData.getName())).willReturn(databaseUser);
//        given(userRepository.save(updatedData)).willReturn(databaseUser);
//
//        userService.updateUser(updatedData);
//
//    }
//
//    @Test
//    public void update_ShouldUpdateUserName(){
//        User databaseUser = User.builder().id(1L).name("sauravi").address("baluwatar").build();
//        User updatedData = User.builder().id(1L).name("sadikshya").address("baluwatar").build();
//
//        given(userRepository.findUser(updatedData.getId(), updatedData.getName(), updatedData.getAddress())).willReturn(null);
//        given(userRepository.findUserByID(updatedData.getId())).willReturn(databaseUser);
//        given(userRepository.findByName(updatedData.getName())).willReturn(null);
//        given(userRepository.save(databaseUser)).willReturn(databaseUser);
//
//        assertFalse(updatedData.getName().equals(databaseUser.getName()));
//        assertThat(userService.updateUser(updatedData)).isEqualTo(databaseUser);
//        assertThat(databaseUser.getName()).isEqualTo(updatedData.getName());
//        verify(userRepository).save(databaseUser);
//
//    }
//
//    @Test
//    public void update_ShouldUpdateUserAddress(){
//        User databaseUser = User.builder().id(1L).name("sauravi").address("baluwatar").build();
//        User updatedData = User.builder().id(1L).name("sauravi").address("patan").build();
//
//        given(userRepository.findUser(updatedData.getId(), updatedData.getName(), updatedData.getAddress())).willReturn(null);
//        given(userRepository.findUserByID(updatedData.getId())).willReturn(databaseUser);
//        given(userRepository.findByName(updatedData.getName())).willReturn(null);
//        given(userRepository.save(databaseUser)).willReturn(databaseUser);
//
//        assertFalse(updatedData.getAddress().equals(databaseUser.getAddress()));
//        assertThat(userService.updateUser(updatedData)).isEqualTo(databaseUser);
//        assertThat(databaseUser.getAddress()).isEqualTo(updatedData.getAddress());
//        verify(userRepository).save(databaseUser);
//    }
//
//    @Test
//    public void fetchAllUsers_ShouldReturnEmptyList(){
//        thrown.expect(DataNotFoundException.class);
//        thrown.expectMessage("Data does not exists");
//
//        given(userRepository.findAllUsers()).willReturn(null);
//
//        userService.fetchAllUser();
//
//    }
//    @Test
//    public void  fetchAllUsers_ShouldReturnList(){
//        List<User> userList= new ArrayList<>();
//        User databaseUser = User.builder().id(1L).name("sauravi").address("baluwatar").build();
//        userList.add(databaseUser);
//
//        given(userRepository.findAllUsers()).willReturn(java.util.Optional.ofNullable(userList));
//
//        assertThat(userService.fetchAllUser()).isEqualTo(userList);
//
//
//
//    }
////
////    @Test
////    public void fetchAllUsers_ShouldReturnList(){
////        List<User> userList= new ArrayList<>();
////        User databaseUser = User.builder().id(1L).name("sauravi").address("baluwatar").build();
////        userList.add(databaseUser);
////
////        given(userRepository.findAllUsers()).willReturn(userList);
////
////        assertThat(userService.fetchAllUser()).isEqualTo(userList);
////
////    }
//
//
//    @Test
//    public void deleteUserById_ShouldReturnDataNotFound(){
//        thrown.expect(DataNotFoundException.class);
//        thrown.expectMessage("Data not found");
//
//        given(userRepository.findUserByID(2L)).willReturn(null);
//
//        userService.deleteUserById(2L);
//    }
//
//    @Test
//    public void deleteUserById_ShouldDeleteData(){
//        User databaseUser = User.builder().id(1L).name("sauravi").address("baluwatar").build();
//
//        given(userRepository.findUserByID(1L)).willReturn(databaseUser);
//
//        assertThat(userService.deleteUserById(1L)).isEqualTo(true);
//        verify(userRepository).delete(databaseUser);
//
//
//
//    }
//
//
//}
