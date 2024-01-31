package com.supercoding.boardservice.service;

import com.supercoding.boardservice.dto.UserDto;
import com.supercoding.boardservice.repository.users.UserEntity;
import com.supercoding.boardservice.repository.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        System.out.println("user" + user);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    public boolean signup(UserDto userDto) {
        String email = userDto.getEmail();
        String password = userDto.getPassword();

        if (userRepository.existsByEmail(email)) {
            return false;
        }
        UserEntity userSingUp = userRepository.save(UserEntity.builder()
                                                .email(email)
                                                .password(passwordEncoder.encode(password))
                                                .build());
        return true;
    }
}
