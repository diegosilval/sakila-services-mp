/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasslatam.sakila.services.endpoints;

import com.vasslatam.sakila.services.domain.Store;
import com.vasslatam.sakila.services.services.StoreService;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

/**
 *
 * @author Diego Silva Limaco <diego.silva at apuntesdejava.com>
 */
@Path("store")
@ApplicationScoped
@Produces(APPLICATION_JSON)
public class StoreEndpoint {

    @Inject
    private StoreService storeService;

    @GET
    public Response findAll() {
        List<Store> stores = storeService.findAll();
        return Response.ok(stores).build();
    }
}
