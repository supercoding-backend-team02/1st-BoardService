package com.supercoding.boardservice.controller;

import com.supercoding.boardservice.dto.UserDTO;
import com.supercoding.boardservice.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO registrationDTO) {
        // 회원가입 서비스 호출
        registrationService.registerNewUser(registrationDTO.getEmail(), registrationDTO.getPassword());
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
}
