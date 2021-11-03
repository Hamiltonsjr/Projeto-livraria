package br.com.livraria.services;

import br.com.livraria.repositories.BookRepository;
import br.com.livraria.responses.BookResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Objects;


@Dependent
public class BookService {

    @Inject
    @RestClient
    public BookRepository repository;

    public boolean isValid(Long bookId){
        if(Objects.isNull(bookId)){
            return true;
        }
        try  {
            BookResponse response = repository.getBook(bookId);
            if(!response.getId().equals(0L)){
                return true;
            }else {
                throw new Exception();
            }

        } catch (Exception e){
            return false;
        }
    }
}

