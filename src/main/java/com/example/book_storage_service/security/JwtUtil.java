package com.example.book_storage_service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt-secret}")
    private String secret;

    public String generateToken() {
        Date expirationDate = Date.from(ZonedDateTime.now().plusDays(1).toInstant());
        return JWT.create()
                .withSubject("Book-storage")
                .withIssuedAt(new Date())
                .withIssuer("book-storage-service")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

}
