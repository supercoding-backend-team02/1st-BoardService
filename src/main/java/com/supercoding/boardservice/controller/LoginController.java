package com.supercoding.boardservice.controller;


import com.supercoding.boardservice.config.JwtTokenProvider;
import com.supercoding.boardservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthenticationException;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final JwtTokenProvider jwtTokenProvider;


    public LoginController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody UserDTO userDto) {
        String token = jwtTokenProvider.createToken(userDto.getEmail());
        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO(token);
        return ResponseEntity.ok(tokenResponseDTO);
    }

    // DTO 클래스 정의
    public static class TokenResponseDTO {
        private String token;

        public TokenResponseDTO(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

}
