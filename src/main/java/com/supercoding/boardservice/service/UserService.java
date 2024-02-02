package com.supercoding.boardservice.service;

import com.supercoding.boardservice.config.JwtTokenProvider;
import com.supercoding.boardservice.dto.UserDto;
import com.supercoding.boardservice.repository.users.UserEntity;
import com.supercoding.boardservice.repository.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final CustomUserDetailsService customUserDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public String login(UserDto userDto) {
        String email = userDto.getEmail();
        String password = userDto.getPassword();

        UserDetails userDetails;
        try{
            userDetails = customUserDetailsService.loadUserByUsername(email);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("등록된 이메일이 아닙니다.");
        }

        boolean passwordCheck = passwordEncoder.matches(password, userDetails.getPassword());

        if (!passwordCheck) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        return jwtTokenProvider.createToken(email);
    }

    public boolean signup(UserDto userDto) {
        String email = userDto.getEmail();
        String password = userDto.getPassword();

        if (userRepository.existsByEmail(email)) {
            throw new DataIntegrityViolationException("이미 존재하는 이메일입니다.");
        }

        if (userRepository.existsByEmail(email)) {
            return false;
        }
        UserEntity userSingUp = userRepository.save(UserEntity.builder()
                                                .email(email)
                                                .password(passwordEncoder.encode(password))
                                                .build());

        return true;
    }

    public boolean logout(String token) {
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 토큰이 유효하다면, 해당 토큰을 무효화하도록 설정
            // 여기에서는 단순히 토큰을 무효화한다고 가정
            // 실제로는 무효화된 토큰을 어떻게 처리할지에 대한 방법을 설정해야 함
            return true;
        }
        return false;
    }
}
