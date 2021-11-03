package br.com.livraria.repositories;

import br.com.livraria.responses.BookResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@RegisterRestClient(configKey = "br.com.livraria.repositories.BookRepository")
public interface BookRepository {

    @GET
    @Path("/{id}")
    BookResponse getBook(@PathParam("id") Long bookId);

}
