package br.com.livraria.services;

import br.com.livraria.repositories.ClientRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Objects;

@Dependent
public final class ClientService {

    @Inject
    @RestClient
    public ClientRepository repository;

    public boolean isValid(Long clientId){
        if(Objects.isNull(clientId)){
            return false;
        }
        try {
            repository.getClient(clientId);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
