//package com.example.book_storage_service.config;
//
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.example.book_storage_service.security.JwtUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Map;
//
//@Component
//public class JwtAuthFilter extends OncePerRequestFilter {
//
//    private final JwtUtil jwtUtil;
//
//    public JwtAuthFilter(JwtUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String jwt = authHeader.substring(7);
//
//            try {
//                Map<String, String> claims = jwtUtil.validateTokenAndRetrieveClaim(jwt);
//
//                String username = claims.get("username");
//
//                if (username != null) {
//                    UsernamePasswordAuthenticationToken authenticationToken =
//                            new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                }
//            } catch (JWTVerificationException e) {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
//                return;
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
