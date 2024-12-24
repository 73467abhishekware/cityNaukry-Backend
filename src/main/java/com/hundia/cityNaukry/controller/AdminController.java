package com.hundia.cityNaukry.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
@CrossOrigin("*")
public class AdminController {

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    @GetMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestParam String email, @RequestParam String password) {
        if (adminEmail.equals(email) && adminPassword.equals(password)) {
            return new ResponseEntity<>("Admin login successful!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid admin credentials.", HttpStatus.UNAUTHORIZED);
        }
    }
    
}