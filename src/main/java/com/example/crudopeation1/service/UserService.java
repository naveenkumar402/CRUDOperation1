package com.example.crudopeation1.service;

import com.example.crudopeation1.Entity.User;
import com.example.crudopeation1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userrepository;
    public String addUser(User user){
       if(userrepository.save(user)!= null)
           return "User added successfully";
       return "User was not created";
    }
    public String updateUser(Integer id,User user){
        if(userrepository.existsById(id)){
            user.setId(id);
            userrepository.save(user) ;
            return "User updated Successfully";
        }
        return "Something Wrong";
    }
    public User viewUser(Integer id){
        return userrepository.getById(id);
    }
    public void deleteUser(Integer id){
        userrepository.deleteById(id);
    }
}
