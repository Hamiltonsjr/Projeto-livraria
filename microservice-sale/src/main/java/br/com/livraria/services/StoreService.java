package br.com.livraria.services;

import br.com.livraria.repositories.StoreRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Objects;

@Dependent
public class StoreService {

    @Inject
    @RestClient
    public StoreRepository repository;

    public boolean isValid(Long storeId){
        if(Objects.isNull(storeId)){
            return false;
        }
        try {
            repository.getStore(storeId);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
