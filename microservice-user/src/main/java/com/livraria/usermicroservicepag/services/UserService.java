package com.livraria.usermicroservicepag.services;

import com.livraria.usermicroservicepag.entities.User;
import com.livraria.usermicroservicepag.exceptions.UserBadRequestException;
import com.livraria.usermicroservicepag.exceptions.UserNotFoundException;
import com.livraria.usermicroservicepag.exceptions.UserUnauthorizedException;
import com.livraria.usermicroservicepag.repositories.UserRepository;
import com.livraria.usermicroservicepag.resource.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    public UserRepository repository;

    @Autowired
    public UserValidProfile profile;


    public List<UserDto> getAll() {
        List<User> users = repository.findAll();
        log.info("Lista de todos os usuários ");
        return UserDto.converter(users);
    }

    public UserDto getById(Long id) {
        Optional<User> user = repository.findById(id);
        if(id == null){
            throw new UserNotFoundException("ID informado não foi encontrado no banco de dados");
        }
        if(user.isPresent()){
            var userDto = new UserDto(user.get());
            log.info("Usuário encontrado {}", userDto);
            return userDto;
        }
        throw new UserNotFoundException("ID informado não foi encontrado no banco de dados");
    }

    public User insert(User user, Long userId) {
        boolean hasValidPermission = profile.isValid(userId);
        if(!hasValidPermission){
            throw new UserUnauthorizedException("Perfil de usuário não tem permissão de acesso");
        }
        Optional<User> optionalUser = repository.findById(user.getId());
        if(optionalUser.isEmpty()){
            log.info("Usuário adicionado com sucesso");
           return repository.save(user);
        }
        throw new UserBadRequestException("Não foi possível criar um novo usuário, verifique se os dados são válidos");
    }


    public void delete(Long id,Long userId) {
        boolean hasValidPermission = profile.isValid(userId);
        if(!hasValidPermission){
            throw new UserUnauthorizedException("Perfil de usuário não tem permissão de acesso");
        }
        Optional<User> obj = repository.findById(id);
        if (obj.isEmpty()){
            throw new UserNotFoundException("ID não existe no banco de dados");
        }else{
            log.info("Usuário deletado com sucesso");
            repository.deleteById(id);
        }
    }

    public User update(User user, Long userId) {
        boolean hasValidPermission = profile.isValid(userId);
        if(!hasValidPermission){
            throw new UserUnauthorizedException("Perfil de usuário não tem permissão de acesso");
        }

        User entity = repository.findById(user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado"));

        entity.setName(user.getName());
        entity.setNickname(user.getNickname());
        entity.setPassword(user.getPassword());
        repository.save(entity);
        return user;
    }
}


