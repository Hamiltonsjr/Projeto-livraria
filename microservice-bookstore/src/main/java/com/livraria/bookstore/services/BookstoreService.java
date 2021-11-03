package com.livraria.bookstore.services;

import com.livraria.bookstore.entities.Bookstore;
import com.livraria.bookstore.exception.BadRequestException;
import com.livraria.bookstore.exception.NotFoundException;
import com.livraria.bookstore.exception.UserUnauthorizedException;
import com.livraria.bookstore.repositories.BookstoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookstoreService {

    @Autowired
    public BookstoreRepository repository;
    @Autowired
    public ViaCepService searchCEP;
    @Autowired
    public UserService userService;

    public List<Bookstore> getAll(){
        log.info("Lista de todas as livrárias no Banco de Dados");
        return repository.findAll();
    }

    public Bookstore getById(Long id){
        Optional<Bookstore> optionalObject = repository.findById(id);
        return optionalObject.orElseThrow(() -> new NotFoundException("Livrária não encontrada"));
    }

    public void update(Bookstore bookstore, Long userId){
        boolean hasValidPermission = userService.isValid(userId);
        if(!hasValidPermission){
            throw new UserUnauthorizedException("Perfil de usuário não tem permissão de acesso");
        }
        Bookstore entity = repository.findById(bookstore.getId()).orElseThrow(() -> new NotFoundException( "Livrária não encontrada"));

        boolean hasValidCEP = searchCEP.isValid(bookstore.getCEP());
        if(!hasValidCEP){
            throw new BadRequestException("CEP informado não é valido ou não existe");
        }
        entity.setTrade(bookstore.getTrade());
        entity.setCompany(bookstore.getCompany());
        entity.setCNPJ(bookstore.getCNPJ());
        repository.save(entity);
    }

    public Bookstore insert(Bookstore bookstore, Long userId){
        boolean hasValidPermission = userService.isValid(userId);
        if(!hasValidPermission){
            throw new UserUnauthorizedException("Perfil de usuário não tem permissão de acesso");
        }
        boolean hasValidCEP = searchCEP.isValid(bookstore.getCEP());
        if(!hasValidCEP){
            throw new BadRequestException("CEP informado não é valido ou não existe");
        }
        Optional<Bookstore> optionalBookstore = repository.findById(bookstore.getId());
        if(optionalBookstore.isEmpty() && hasValidCEP){
            log.info("Livrária cadastrada com sucesso");
            return repository.save(bookstore);
        }
        throw new BadRequestException("ID inválido");
    }

    public void delete(Long id, Long userId){
        boolean hasValidPermission = userService.isValid(userId);
        if(!hasValidPermission){
            throw new UserUnauthorizedException("Perfil de usuário não tem permissão de acesso");
        }
        Optional<Bookstore> OptionalObject = repository.findById(id);
        if(OptionalObject.isEmpty()){
            throw new NotFoundException("Livrária não encontrada no Banco de Dados");
        }
        else
            log.info("Cadastro de livrária deletado com sucesso");
            repository.deleteById(id);
    }
}
