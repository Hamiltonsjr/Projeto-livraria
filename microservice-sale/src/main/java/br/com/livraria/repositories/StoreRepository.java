package br.com.livraria.repositories;

import br.com.livraria.responses.BookstoreResponse;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@RegisterRestClient(configKey = "br.com.livraria.repositories.StoreRepository")
public interface StoreRepository {

    @GET
    @Path("/getId/{id}")
    BookstoreResponse getStore(@PathParam("id") Long id);
}
