package br.com.livraria.services;

import br.com.livraria.entities.Sell;
import br.com.livraria.exceptions.SaleBadRequestException;
import br.com.livraria.exceptions.SaleNotFoundException;
import br.com.livraria.exceptions.UserUnauthorizedException;
import br.com.livraria.repositories.SaleRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.List;
import java.util.Optional;

@Slf4j
@ApplicationScoped
public class SaleService {

    @Inject
    public SaleRepository repository;

    @Inject
    public UserService userService;

    @Inject
    public ClientService clientService;

    @Inject
    public StoreService storeService;

    @Inject
    public BookService bookService;

    public List<Sell> getAll() {
        log.info("Lista de todas a vendas no Banco de dados");
        return repository.findAll();
    }

    public Sell getId(Long id) {
        Optional<Sell> optionalSale = repository.findById(id);
        return optionalSale.orElseThrow(() -> new SaleNotFoundException("Não possui vendas com o ID informado"));
    }

    public Sell update(Sell sell, Long userId) {
        boolean hasValidUser = userService.isValid(userId);
        if(!hasValidUser){
            throw new UserUnauthorizedException("Perfil de usuário não tem permissão de acesso");
        }
        boolean hasValidBookstore = storeService.isValid(sell.getLoja());
        if(!hasValidBookstore){
            throw new SaleBadRequestException("Livrária não encontrada");
        }
        boolean hasValidClient = clientService.isValid(sell.getCliente());
        if(!hasValidClient){
            throw new SaleBadRequestException("Cliente não encontrado");
        }
        boolean hasValidSeller = userService.isValidSeller(sell.getVendedor());
        if (!hasValidSeller){
            throw new SaleBadRequestException("Vendedor não encontrado");
        }
        boolean hasValidBook = bookService.isValid(sell.getLivro());
        if (!hasValidBook) {
            throw new SaleBadRequestException("Livro não encontrado no estoque");
        }

        Sell sellEntity = repository.findById(sell.getId()).orElseThrow(() ->
                new SaleNotFoundException("Cadastro de venda não pode ser atualizado, por favor check as informações"));

        sellEntity.setVendedor(sell.getVendedor());
        sellEntity.setCliente(sell.getCliente());
        sellEntity.setLoja(sell.getLoja());
        sellEntity.setLivro(sell.getLivro());

        repository.save(sellEntity);

        log.info("Venda atualizada");
        return sell;
    }

    public Sell insert(Sell sell, Long userId){

        boolean hasValidUser = userService.isValid(userId);
        if(!hasValidUser){
            throw new UserUnauthorizedException("Perfil de usuário não tem permissão de acesso");
        }
        boolean hasValidBookstore = storeService.isValid(sell.getLoja());
        if(!hasValidBookstore){
            throw new SaleBadRequestException("Livrária não encontrado");
        }
        boolean hasValidClient = clientService.isValid(sell.getCliente());
        if(!hasValidClient){
            throw new SaleBadRequestException("Cliente não encontrado");
        }
        boolean hasValidSeller = userService.isValidSeller(sell.getVendedor());
        if (!hasValidSeller){
            throw new SaleBadRequestException("Vendedor não encontrado");
        }
        boolean hasValidBook = bookService.isValid(sell.getLivro());
        if (!hasValidBook) {
            throw new SaleBadRequestException("Livro não encontrado no estoque");
        }

        Optional<Sell> optionalSale = repository.findById(sell.getId());
        if(optionalSale.isEmpty()){
            repository.save(sell);
            log.info("Venda Cadastrada"+ sell);
            return sell;
        }
        throw new SaleBadRequestException("Venda não cadastrada");
    }

    public void delete(Long id, Long userId){
        boolean hasValidUser = userService.isValid(userId);
        if(!hasValidUser){
            throw new UserUnauthorizedException("Perfil de usuário não tem permissão de acesso");
        }
        Optional<Sell> optionalSale = repository.findById(id);
        if(optionalSale.isEmpty()){
            throw new SaleNotFoundException("Venda não encontrada no Banco de dados");
        }else{
            log.info("Venda deletada com sucesso");
            repository.deleteById(id);
        }
    }
}
