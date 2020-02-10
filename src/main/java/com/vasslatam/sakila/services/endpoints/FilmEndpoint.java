/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasslatam.sakila.services.endpoints;

import com.vasslatam.sakila.services.domain.Film;
import com.vasslatam.sakila.services.services.FilmService;
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
@Path("film")
@Produces(MediaType.APPLICATION_JSON)
public class FilmEndpoint {

    @Inject
    private FilmService filmService;

    @GET
    public Response findAll() {
        List<Film> films = filmService.findAll();
        return Response.ok(films).build();
    }
}
