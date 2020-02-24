/*
 * Copyright 2020 Diego Silva Limaco <diego.silva at apuntesdejava.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vasslatam.sakila.services.endpoints;

import com.vasslatam.sakila.services.domain.Actor;
import com.vasslatam.sakila.services.domain.Film;
import com.vasslatam.sakila.services.services.ActorService;
import com.vasslatam.sakila.services.services.FilmService;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author diego
 */
@Path("film")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class FilmEndpoint {

    @Inject
    private ActorService actorService;

    @Inject
    private FilmService filmService;

    @GET
    public Response findAll() {
        List<Film> films = filmService.findAll();
        return Response.ok(films).build();
    }

    @GET
    @Path("by-actor")
    public Response findByActor(@QueryParam("actor") String actorName) {
        List<Actor> actors = actorService.findByName(actorName);
        if (actors.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
//        Actor actor = actors.get(0);
//        List<Film> films = filmService.findByActor(actor);
        List<Film> films = filmService.findByActors(actors);
        return Response.ok(films).build();
    }
    @GET
    @Path("store/{storeId}")
    public Response findByStore(@PathParam("storeId") int storeId) {
        List<Film> films = filmService.findByStoreId(storeId);
        return Response.ok(films).build();
    }
}
