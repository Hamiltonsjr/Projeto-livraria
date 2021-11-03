package com.livraria.usermicroservicepag.repositories;

import com.livraria.usermicroservicepag.resource.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://usuario:8084/usuario", name = "ms-user")
public interface UserResponse {

    @GetMapping("/getId/{id}")
    UserDto findByProfile(@PathVariable("id") String userId);
}
