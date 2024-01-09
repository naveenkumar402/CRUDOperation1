package com.example.crudopeation1.controller;

import com.example.crudopeation1.Entity.User;
import com.example.crudopeation1.exceptionhandling.UserAlreadyExistsException;
import com.example.crudopeation1.exceptionhandling.UserNotFoundException;
import com.example.crudopeation1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userservice;
    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody User user){
        try {
            userservice.addUser(user);
            return new ResponseEntity<>("User added successfully",HttpStatus.OK);
        } catch (UserAlreadyExistsException ex) {
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        } catch (Exception ex) {
            throw new RuntimeException("Internal Server Error");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody User user) {
        try {
            userservice.updateUser(id, user);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            throw new UserNotFoundException("User not found with id: " + id);
        } catch (Exception ex) {
            throw new RuntimeException("Internal Server Error");
        }
    }
    @GetMapping("/view/{id}")
    public ResponseEntity<User> viewUser(@PathVariable Integer id){
        User user = userservice.viewUser(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
        try {
            userservice.deleteUser(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            throw new UserNotFoundException("User not found with id: " + id);
        } catch (Exception ex) {
            throw new RuntimeException("Internal Server Error");
        }
    }

}
