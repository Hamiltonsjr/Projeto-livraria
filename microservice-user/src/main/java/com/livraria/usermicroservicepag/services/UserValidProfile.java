package com.livraria.usermicroservicepag.services;

import com.livraria.usermicroservicepag.repositories.UserRepository;
import com.livraria.usermicroservicepag.repositories.UserResponse;
import com.livraria.usermicroservicepag.resource.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserValidProfile {

    @Autowired
    public UserResponse response;

    public boolean isValid(Long userId){
        if(Objects.isNull(userId)){
            return true;
        }
        try {
            UserDto dto = response.findByProfile(userId.toString());
            if(dto.getProfile().equals("Administrator")){
                return true;
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            return false;
        }
    }
}
