package com.example.book_storage_service.service.implementation;

//import com.example.book_storage_service.model.User;
//import com.example.book_storage_service.repository.UserRepository;
//import com.example.book_storage_service.security.UserDetailsImpl;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> userOptional = userRepository.findByUsername(username);
//        if (userOptional.isEmpty()){
//            throw new UsernameNotFoundException("User not found!");
//        }
//        return new UserDetailsImpl(userOptional.get());
//    }
//}
