/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Son.Maria.controller;

import com.Son.Maria.dal.repository.UserRepository;
import com.Son.Maria.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author roboc
 */
@RestController
public class UserController {
    
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @PostMapping ("/users")
    @ResponseStatus (HttpStatus.CREATED)
    @Transactional (propagation = Propagation.REQUIRES_NEW)
    public void createUser (@RequestBody User user){
            String userid;
            boolean userAlreadyExist;
            
            userid = user.getUserid();
            userAlreadyExist = userRepository.existsById(userid);
            if(userAlreadyExist == true){
                throw new RuntimeException();
            }
            else{
                userRepository.save(user);
            }
    }
}
