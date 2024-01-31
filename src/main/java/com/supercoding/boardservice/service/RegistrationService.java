package com.supercoding.boardservice.service;

import com.supercoding.boardservice.repository.users.UserEntity;
import com.supercoding.boardservice.repository.users.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerNewUser(String email, String password) {
        // 사용자가 입력한 비밀번호를 해싱하여 저장
        String hashedPassword = passwordEncoder.encode(password);

        // 새로운 사용자 엔터티 생성
        UserEntity newUser = new UserEntity();
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);

        // 데이터베이스에 새로운 사용자 저장
        userRepository.save(newUser);
    }
}
