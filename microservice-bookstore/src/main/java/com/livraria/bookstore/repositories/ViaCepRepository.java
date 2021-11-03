package com.livraria.bookstore.repositories;

import com.livraria.bookstore.entities.Bookstore;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
public interface ViaCepRepository {

    @GetMapping("{cep}/json")
    Optional<Bookstore> getCEP(@PathVariable("cep") String cep);

}
