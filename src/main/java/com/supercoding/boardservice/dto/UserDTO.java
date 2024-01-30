package com.supercoding.boardservice.dto;

public class UserDTO {
    private String email;
    private String password;

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail(String email) {
        return this.email = email;
    }

}
