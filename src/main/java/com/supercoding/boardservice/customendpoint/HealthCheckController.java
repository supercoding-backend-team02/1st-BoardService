package com.supercoding.boardservice.customendpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class HealthCheckController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        try (Connection connection = dataSource.getConnection()) {
            // 데이터베이스 연결이 성공하면 "OK"를 반환
            return ResponseEntity.ok("OK");
        } catch (SQLException e) {
            // 데이터베이스 연결 실패 시 에러 메시지를 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database connection error");
        }
    }
}
