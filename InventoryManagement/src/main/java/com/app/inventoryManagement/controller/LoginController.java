package com.app.inventoryManagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.inventoryManagement.model.LoginRequest;
import com.app.inventoryManagement.service.LoginService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React frontend
public class LoginController {
	public Logger logger=LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest)
    {
    	logger.info("Inside the login");
    	logger.info("UserName["+loginRequest.getUsername()+"]");
    	logger.info("Password["+loginRequest.getPassword()+"]");
        return loginService.validateLogin(loginRequest);
    }
}

