package com.supercoding.boardservice.config;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class JwtAuthenticationArgumentResolver implements HandlerMethodArgumentResolver {
    private JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationArgumentResolver(JwtTokenProvider jwtTokenProvider) {
        Objects.requireNonNull(jwtTokenProvider, "jwtTokenProvider must not be null");
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Authentication.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        String token = jwtTokenProvider.resolveToken(request);
        try {
            String token = jwtTokenProvider.resolveToken(request);
            return jwtTokenProvider.getAuthentication(token);
        } catch (Exception e) {
            // 예외 처리 로직 추가
            throw new RuntimeException("Failed to resolve authentication from token", e);
        }

//        return jwtTokenProvider.getAuthentication(token);
    }
}
