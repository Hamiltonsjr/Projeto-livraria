package br.com.livraria.resources;

import br.com.livraria.entities.Client;
import br.com.livraria.services.ClientService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/cliente")
@RequestScoped
public class ClientResource {

    @Inject
    public ClientService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Client> clients = service.getAll();
        return Response.ok(clients).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getId/{id}")
    public Response getId(@PathParam("id") Long id) {
        Optional<Client> optionalClient = Optional.ofNullable(service.getId(id));
        return Response.ok(optionalClient).build();
    }

    @POST
    @Path("insert/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(@HeaderParam("userId") Long userId, Client client) {
        service.insert(client, userId);
        return Response.ok(client).build();
    }

    @PUT
    @Path("update/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id,@HeaderParam("userId") Long userId, Client client) {
        Client entity = service.update(client,userId);
        return Response.ok(entity).build();
    }

    @DELETE
    @Path("delete/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id, @HeaderParam("userId") Long userId) {
        Response.status(Response.Status.OK).build();
        service.delete(id,userId);
    }
}

