package com.example.crudopeation1.service.service;

import com.example.crudopeation1.Entity.User;
import com.example.crudopeation1.repository.UserRepository;
import com.example.crudopeation1.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setId(1);
        user.setName("Naveenkumar");
        user.setEmail("naveen@gmail.com");
        user.setMobile(1234567890L);
        when(userService.addUser(user)).thenReturn("User added Successfully");
    }
    @Test
    public void testGetUser(){
        int id=1;
        when(userService.viewUser(id)).thenReturn(new User());
    }
    @Test
    public void testUpdateUser(){
        int id=1;
        User user = new User();
        user.setName("Naveenkumar");
        user.setEmail("naveen@gmail.com");
        user.setMobile(1234567890L);
        when(userService.updateUser(id,user)).thenReturn("User Updated Successfully");
    }
    @Test
    public void testDeleteUser(){
        int id=1;
        userService.deleteUser(id);
        assertNull(userService.viewUser(id));
    }

}