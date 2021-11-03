package br.com.livraria.resources;

import br.com.livraria.entities.Sell;
import br.com.livraria.services.SaleService;
import org.springframework.web.context.annotation.RequestScope;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/venda")
@RequestScope
public class SaleResource {

    @Inject
    public SaleService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Sell> sells = service.getAll();
        return Response.ok(sells).build();
    }

    @GET
    @Path("getId/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getId(@PathParam("id") Long id){
        Optional<Sell> optionalSale = Optional.ofNullable(service.getId(id));
        return Response.ok(optionalSale).build();
    }

    @POST
    @Path("insert/")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@HeaderParam("userId") Long userId, Sell sell, @PathParam("id") Long id){
        service.insert(sell, userId);
        return Response.ok(sell).build();
    }

    @DELETE
    @Path("delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id, @HeaderParam("userId") Long userId) {
        service.delete(id, userId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("update/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, @HeaderParam("userId") Long userId, Sell sell){
        Sell sellEntity = service.update(sell, userId);
        return Response.ok(sellEntity).build();
    }
}
