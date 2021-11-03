package br.com.livraria.services;

import br.com.livraria.entities.Client;
import br.com.livraria.exceptions.ClientBadRequestException;
import br.com.livraria.exceptions.ClientNotFoundException;
import br.com.livraria.exceptions.UserUnauthorizedException;
import br.com.livraria.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Slf4j
@ApplicationScoped
public class ClientService {

    @Inject
    public ClientRepository repository;
    @Inject
    public ViaCepService serviceCEP;
    @Inject
    public UserService userService;

    public List<Client> getAll(){
        log.info("Lista de todos os clientes no Bando de Dados");
        return repository.findAll();
    }

    public Client getId(Long id){
        Optional<Client> optionalClient = repository.findById(id);
        return optionalClient.orElseThrow(()-> new ClientNotFoundException("Não possui cliente com o ID informado"));
    }

    public Client insert(Client client, Long userId){
        boolean hasValidUser = userService.isValid(userId);
        if(!hasValidUser){
            throw new UserUnauthorizedException("Perfil de usuário não tem permissão de acesso");
        }
        boolean hasValidCep = serviceCEP.isValid(client.getZipCode());
        if(!hasValidCep){
            throw new ClientBadRequestException("CEP informado não é válido ou não existe");
        }
        Optional<Client> optionalClient = repository.findById(client.getId());
        if(optionalClient.isEmpty() && hasValidCep){
            repository.save(client);
            return client;
        }
        throw new ClientBadRequestException("Cliente não pode ser cadastrado");
    }

    public Client update(Client client, Long userId) {
        boolean hasValidUser = userService.isValid(userId);
        if(!hasValidUser){
            throw new UserUnauthorizedException("Perfil de usuário não tem permissão de acesso");
        }
        boolean hasValidCep = serviceCEP.isValid(client.getZipCode());
        if(!hasValidCep){
            throw new ClientBadRequestException("CEP informado não é valido ou não existe");
        }
        Client entity = repository.findById(client.getId()).orElseThrow(() ->
                new ClientNotFoundException("Cadastro do cliente não pode ser atualizado, for favor check as informações"));

        entity.setName(client.getName());
        entity.setCPF(client.getCPF());
        entity.setAddress(client.getAddress());
        entity.setCity(client.getCity());
        entity.setZipCode(client.getZipCode());
        repository.save(client);
        return client;
    }

    public void delete(Long id, Long userId){
        boolean hasValidUser = userService.isValid(userId);
        if(!hasValidUser){
            throw new UserUnauthorizedException("Perfil de usuário não tem permissão de acesso");
        }
        Optional<Client> optionalClient = repository.findById(id);
        if(optionalClient.isEmpty()){
            throw new ClientNotFoundException("Cliente não encontrado no Banco de dados");
        }else{
            log.info("Cliente deletado com sucesso ");
            repository.deleteById(id);
        }
    }
}
