package br.com.livraria.repositories;

import br.com.livraria.entities.Client;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Optional;

@RegisterRestClient(configKey = "br.com.livraria.repositories.CepRepository")
public interface CepRepository {

    @GET
    @Path("/{cep}/json")
    Optional<Client> validCEP(@PathParam("cep") String cep);
}
