package com.app.inventoryManagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.inventoryManagement.model.LoginRequest;
import com.app.inventoryManagement.model.User;
import com.app.inventoryManagement.repository.UserRepository;

import java.util.Optional;

@Service
public class LoginService {
	public Logger logger=LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> validateLogin(LoginRequest loginRequest) {
    	logger.info("Inside the validate Loging");
    	logger.info("Inside the validation part");
        Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());

        return userOpt
                .filter(user -> user.getPassword().equals(loginRequest.getPassword()))  // Check if password matches
                .map(user -> {
                    if (user.getIsActive() == 1) {
                        return new ResponseEntity<>("User is not active-prasanth", HttpStatus.FORBIDDEN);
                    }
                    return new ResponseEntity<>("Login Successful, kindly proceed further", HttpStatus.OK);  // Successful login
                })
                .orElseGet(() -> new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED));  // Invalid username or password
    }
}

