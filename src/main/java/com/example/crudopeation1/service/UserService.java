package com.example.crudopeation1.service;

import com.example.crudopeation1.Entity.User;
import com.example.crudopeation1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userrepository;
    public User addUser(User user){
       return userrepository.save(user);
    }
    public User updateUser(Integer id,User user){
        if(userrepository.existsById(id)){
            user.setId(id);
            return userrepository.save(user) ;
        }
        return null;
    }
    public User viewUser(Integer id){
        return userrepository.getById(id);
    }
    public void deleteUser(Integer id){
        userrepository.deleteById(id);
    }
}
