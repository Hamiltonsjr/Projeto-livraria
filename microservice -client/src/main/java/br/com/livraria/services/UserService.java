package br.com.livraria.services;

import br.com.livraria.repositories.UserRepository;
import br.com.livraria.responses.UserResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Objects;

@Dependent
public final class UserService {

    @Inject
    @RestClient
    public UserRepository repository;

    public boolean isValid(Long userId){
        if(Objects.isNull(userId)){
            return true;
        }
        try {
            UserResponse response = repository.getUser(userId.toString());
            if(response.getProfile().equals("Administrator") && response.getId() != null){
                return true;
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            return false;
        }
    }
}
