package com.supercoding.boardservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LogoutController {

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // 현재 인증된 사용자의 정보를 가져옵니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 여기에서 토큰 무효화 또는 로그아웃 처리를 수행합니다.
        // 예시: 토큰 무효화, 세션 초기화 등

        // 로그아웃이 성공하면 성공 메시지를 반환합니다.
        return ResponseEntity.ok("Logout successful");
    }
}
