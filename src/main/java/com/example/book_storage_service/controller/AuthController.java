package com.example.book_storage_service.controller;

//import com.example.book_storage_service.dto.UserDto;
//import com.example.book_storage_service.model.User;
//import com.example.book_storage_service.service.implementation.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//    private final AuthService authService;
//
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//
//    @PostMapping("/registration")
//    public String performRegistration(@RequestBody @Valid UserDto userDto, BindingResult bindingResult){
//        if (bindingResult.hasErrors()){
//            throw new ValidationException(bindingResult.getAllErrors().toString());
//        }
//        authService.registerUser(userDto);
//        return "User registered successfully!";
//    }
//
//    @PostMapping("/login")
//    public Map<String, String> performLogin(@RequestBody UserDto userDto){
//        String jwt = authService.authenticateUser(userDto.getUsername(), userDto.getPassword());
//        Map<String, String> response = new HashMap<>();
//        response.put("jwt-token", jwt);
//        return response;
//    }
//}
