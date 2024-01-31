package com.supercoding.boardservice.service;

import com.supercoding.boardservice.repository.users.UserEntity;
import com.supercoding.boardservice.repository.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 사용자 이메일을 기반으로 데이터베이스에서 사용자 정보를 조회
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Spring Security의 UserDetails 구현체를 반환하여 인증 및 권한 부여
        return new User(
                user.getEmail(),        // 사용자의 이메일
                user.getPassword(),     // 사용자의 암호화된 패스워드
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))  // 사용자의 권한 (여기서는 단일 권한 "ROLE_USER"를 가정)
        );
    }
}