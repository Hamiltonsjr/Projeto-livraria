package com.livraria.bookstore.services;

import com.livraria.bookstore.repositories.UserRepository;
import com.livraria.bookstore.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    public UserRepository repository;

    public boolean isValid(Long userId) {
        if (Objects.isNull(userId)) {
            return true;
        }
        try {
            UserResponse userResponse = repository.getUser(userId.toString());
            if(userResponse.getProfile().equals("Administrator") &&
            userResponse.getId() != null){
                return true;
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            return false;
        }
    }
}
