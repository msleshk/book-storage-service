package com.example.book_storage_service.feign;

import com.example.book_storage_service.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "book-tracker-service", url = "${tracker.service.url}", configuration = FeignConfig.class)
public interface BookTrackerFeignClient {

    @PostMapping("/book-status/{id}")
    void notifyBookCreated(@PathVariable("id") Long bookId);

    @DeleteMapping("/book-status/{id}")
    void notifyBookDeleted(@PathVariable("id") Long bookId);
}

