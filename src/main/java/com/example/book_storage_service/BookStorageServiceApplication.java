package com.example.book_storage_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.book_storage_service.feign")
public class BookStorageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStorageServiceApplication.class, args);
    }

}
