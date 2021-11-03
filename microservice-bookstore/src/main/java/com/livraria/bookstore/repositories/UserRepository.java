package com.livraria.bookstore.repositories;

import com.livraria.bookstore.user.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://usuario:8084/usuario", name = "ms-user")
public interface UserRepository {

    @GetMapping("/getId/{id}")
    UserResponse getUser(@PathVariable("id") String userId);
}
