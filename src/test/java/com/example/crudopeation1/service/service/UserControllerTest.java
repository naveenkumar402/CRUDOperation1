package com.example.crudopeation1.service.service;
import com.example.crudopeation1.Entity.User;
import com.example.crudopeation1.controller.UserController;
import com.example.crudopeation1.service.UserService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)

public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("Naveenkumar");
        user.setEmail("naveen@gmail.com");
        user.setMobile(1234567890L);
        when(userService.addUser(any(User.class))).thenReturn("User added successfully");
       mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .content("{ \"name\": \"Naveenkumar\", \"email\": \"naveen@gmail.com\", \"mobile\": 1234567890 }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("User added successfully"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        Integer userId = 1;
        User user = new User();
        user.setName("Updated Name");
        user.setEmail("updated.email@example.com");
        user.setMobile(9876543210L);
        when(userService.updateUser(userId, user)).thenReturn("User updated successfully");
        mockMvc.perform(MockMvcRequestBuilders.put("/update/{id}", userId)
                        .content("{ \"name\": \"Updated Name\", \"email\": \"updated.email@example.com\", \"mobile\": 9876543210 }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("User updated successfully"));
    }

    @Test
    public void testViewUser() throws Exception {
        // Given
        Integer userId = 1;
        User user = new User();
        user.setId(userId);
        user.setName("Naveenkumar");
        user.setEmail("naveen@gmail.com");
        user.setMobile(1234567890L);
        when(userService.viewUser(userId)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/view/{id}", userId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Naveenkumar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("naveen@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mobile").value(1234567890));
    }

    @Test
    public void testDeleteUser() throws Exception {
        Integer userId = 1;
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/{id}", userId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

