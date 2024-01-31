package com.supercoding.boardservice.controller;


import com.supercoding.boardservice.config.JwtTokenProvider;
import com.supercoding.boardservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    private final JwtTokenProvider jwtTokenProvider;


    public LoginController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/api/v1/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDto) {
        String token = jwtTokenProvider.createToken(userDto.getEmail());
        return ResponseEntity.ok(token);
    }

}
