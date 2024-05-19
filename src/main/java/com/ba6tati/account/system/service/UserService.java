package com.ba6tati.account.system.service;

import java.util.*;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ba6tati.account.system.model.UserModel;
import com.ba6tati.account.system.repository.UserRepository;

@Service
public class UserService {

    // @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public UserModel registerUser(String username, String email, String password) {
        if (username != null && password != null) {	
            UserModel userModel = new UserModel();
            userModel.setUsername(username);
            userModel.setEmail(email);
            userModel.setPassword(password);

            return userRepository.save(userModel);
        }

        return null;
    }

    public boolean authenticate(String username, String password) {
        // Optional<UserModel> authenticated = userRepository.findByUsernameAndPassword(username, password);
        System.out.println("Authenticated: " + userRepository.findByUsernameAndPassword(username, password).isPresent());
        if (userRepository.findByUsernameAndPassword(username, password).isPresent()) {
            // UserModel user = authenticated.get();
            
            return true;
        }
        return false;
        // return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }
}
