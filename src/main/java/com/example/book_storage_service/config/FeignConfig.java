package com.example.book_storage_service.config;

import com.example.book_storage_service.security.JwtUtil;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    private final JwtUtil jwtUtil;

    public FeignConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token = jwtUtil.generateToken();
            requestTemplate.header("Authorization", "Bearer " + token);
        };
    }
}
