package br.com.livraria.services;

import br.com.livraria.exceptions.ClientNotFoundException;
import br.com.livraria.repositories.CepRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Objects;

@Dependent
public final class ViaCepService{

    @Inject
    @RestClient
    CepRepository repository;

    public boolean isValid(String cep) throws ClientNotFoundException {
        if(Objects.isNull(cep)){
            return false;
        }try {
            repository.validCEP(cep);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
