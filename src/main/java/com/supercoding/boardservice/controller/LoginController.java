package com.supercoding.boardservice.controller;


import com.supercoding.boardservice.config.JwtTokenProvider;
import com.supercoding.boardservice.dto.UserDto;
import com.supercoding.boardservice.repository.Message;
import com.supercoding.boardservice.repository.users.UserEntity;
import com.supercoding.boardservice.repository.users.UserRepository;
import com.supercoding.boardservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {


    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {

        String email = userDto.getEmail();

        UserEntity useremail = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 이메일입니다."));

        String token = jwtTokenProvider.createToken(email);
        System.out.println("token" + token);
        return ResponseEntity.ok(token);
    }

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    public Message signup(@RequestBody UserDto userDto) {
        boolean isSuccess = userService.signup(userDto);

        Message message = new Message();
        if (isSuccess) {
            message.setMessage("회원가입 성공");
        } else {
            message.setMessage("회원가입 실패");
        }

        return message;
    }

}
