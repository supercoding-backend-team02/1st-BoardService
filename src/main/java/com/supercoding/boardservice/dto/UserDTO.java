package com.supercoding.boardservice.dto;

public class UserDTO {
    private String email;
    private String password;

    // 기본 생성자 추가
    public UserDTO() {
    }

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
