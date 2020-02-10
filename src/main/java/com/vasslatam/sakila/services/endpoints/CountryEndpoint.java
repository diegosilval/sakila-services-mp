/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasslatam.sakila.services.endpoints;

import com.vasslatam.sakila.services.domain.Country;
import com.vasslatam.sakila.services.services.CountryService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author diego
 */
@Path("country")
@Produces(MediaType.APPLICATION_JSON)
public class CountryEndpoint {

    @Inject
    private CountryService countryService;

    @GET
    public Response findAll() {
        List<Country> countries = countryService.findAll();

        return Response.ok(countries).build();
    }
}
