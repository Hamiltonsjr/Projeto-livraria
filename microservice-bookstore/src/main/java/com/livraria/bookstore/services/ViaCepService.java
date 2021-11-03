package com.livraria.bookstore.services;

import com.livraria.bookstore.exception.NotFoundException;
import com.livraria.bookstore.repositories.ViaCepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ViaCepService {

    @Autowired
    public ViaCepRepository repository;

    public boolean isValid(String cep) throws NotFoundException {
        if (Objects.isNull(cep)) {
            return false;
        }
        try {
            repository.getCEP(cep);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
