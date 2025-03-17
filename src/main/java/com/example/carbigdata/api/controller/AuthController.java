package com.example.carbigdata.api.controller;

import com.example.carbigdata.api.config.JWTUtil;
import com.example.carbigdata.api.config.UserCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AuthController {


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredentials credentials) {
        String token = JWTUtil.generateToken(credentials.username());
        return ResponseEntity.ok(token);
    }
}
