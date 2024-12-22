package com.example.book_storage_service.service.implementation;

//import com.example.book_storage_service.dto.UserDto;
//import com.example.book_storage_service.exceptions.UserAlreadyExistsException;
//import com.example.book_storage_service.model.User;
//import com.example.book_storage_service.repository.UserRepository;
//import com.example.book_storage_service.security.JwtUtil;
//import com.example.book_storage_service.util.Mapper;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthService {
//
//    private final PasswordEncoder passwordEncoder;
//    private final UserRepository userRepository;
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtil jwtUtil;
//    private final Mapper<User, UserDto> userMapper;
//
//    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil,
//                       @Qualifier("userMapper") Mapper<User, UserDto> userMapper) {
//        this.passwordEncoder = passwordEncoder;
//        this.userRepository = userRepository;
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
//        this.userMapper = userMapper;
//    }
//
//    public String authenticateUser(String username, String password) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password)
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return jwtUtil.generateToken(authentication);
//    }
//
//    public void registerUser(UserDto userDto) {
//        User user = userMapper.toEntity(userDto);
//        if (userRepository.existsByUsername(user.getUsername())) {
//            throw new UserAlreadyExistsException("Such user already exists!");
//        }
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//    }
//
//    public void deleteUser() {
//        // todo
//    }
//}
