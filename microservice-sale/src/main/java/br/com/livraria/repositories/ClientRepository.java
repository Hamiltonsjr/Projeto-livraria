package br.com.livraria.repositories;

import br.com.livraria.responses.ClientResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Optional;

@RegisterRestClient(configKey = "br.com.livraria.repositories.ClientRepository")
public interface ClientRepository {

    @GET
    @Path("/getId/{id}")
    Optional<ClientResponse> getClient(@PathParam("id") Long client);
}
