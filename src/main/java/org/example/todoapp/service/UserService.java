package org.example.todoapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.example.todoapp.repository.UserRepository;
import org.example.todoapp.model.User;

import java.util.List;


@Service
@Transactional
public class UserService{

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllusers(){
        return userRepository.findAll();
    }

    public User findUser(String username){
        return userRepository.findByUsername(username);
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
    
}
