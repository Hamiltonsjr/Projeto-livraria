package br.com.livraria.repositories;
import br.com.livraria.responses.UserResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@RegisterRestClient(configKey = "br.com.livraria.repositories.UserRepository")
public interface UserRepository {

    @GET
    @Path("/getId/{id}")
    UserResponse getUser(@PathParam("id") String userId);

    @GET
    @Path("/getId/{id}")
    UserResponse getSeller(@PathParam("id") Long sellerId);
}
