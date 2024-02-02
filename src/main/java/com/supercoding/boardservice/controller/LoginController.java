package com.supercoding.boardservice.controller;

import com.supercoding.boardservice.dto.UserDto;
import com.supercoding.boardservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final UserService userService;
    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDto userDto) {
        String token = userService.login(userDto);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return ResponseEntity.ok(tokenMap);
    }

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    public ResponseEntity<Boolean> signup(@RequestBody UserDto userDto) {

        boolean isSuccess = userService.signup(userDto);

        return ResponseEntity.ok(isSuccess);
    }

}
