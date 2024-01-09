package com.example.crudopeation1.controller;

import com.example.crudopeation1.Entity.User;
import com.example.crudopeation1.exceptionhandling.UserAlreadyExistsException;
import com.example.crudopeation1.exceptionhandling.UserNotFoundException;
import com.example.crudopeation1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userservice;

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);
    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            logger.info("User added successfully: {}", user);
            return new ResponseEntity<>(userservice.addUser(user), HttpStatus.OK);
        } catch (UserAlreadyExistsException ex) {
            logger.warn("User already exists with email: {}", user.getEmail());
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        } catch (Exception ex) {
            logger.error("Internal Server Error while adding user", ex);
            throw new RuntimeException("Internal Server Error");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        try {
            userservice.updateUser(id, user);
            logger.info("User with id {} updated successfully", id);
            return new ResponseEntity<>(userservice.updateUser(id, user), HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            logger.warn("User not found with id: {}", id);
            throw new UserNotFoundException("User not found with id: " + id);
        } catch (Exception ex) {
            logger.error("Internal Server Error while updating user with id: {}", id, ex);
            throw new RuntimeException("Internal Server Error");
        }
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<User> viewUser(@PathVariable Integer id) {
        try {
            User user = userservice.viewUser(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            logger.warn("User not found with id: {}", id);
            throw new UserNotFoundException("User not found with id: " + id);
        } catch (Exception ex) {
            logger.error("Internal Server Error while viewing user with id: {}", id, ex);
            throw new RuntimeException("Internal Server Error");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        try {
            userservice.deleteUser(id);
            logger.info("User with id {} deleted successfully", id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            logger.warn("User not found with id: {}", id);
            throw new UserNotFoundException("User not found with id: " + id);
        } catch (Exception ex) {
            logger.error("Internal Server Error while deleting user with id: {}", id, ex);
            throw new RuntimeException("Internal Server Error");
        }
    }

}
