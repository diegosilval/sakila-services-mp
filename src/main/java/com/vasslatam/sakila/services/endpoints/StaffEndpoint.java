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

import com.vasslatam.sakila.services.domain.Staff;
import com.vasslatam.sakila.services.services.StaffService;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

/**
 *
 * @author Diego Silva Limaco <diego.silva at apuntesdejava.com>
 */
@Path("staff")
@Produces(APPLICATION_JSON)
@ApplicationScoped
public class StaffEndpoint {

    private static final Logger LOGGER = Logger.getLogger(StaffEndpoint.class.getName());

    @Inject
    private StaffService staffService;

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") int id) {
        Staff staff = staffService.findById(id);
        if (staff == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(staff).build();
    }

    @GET
    @Path("store/{storeId}")
    public Response findByStoreId(@PathParam("storeId") int storeId) {
        Staff staff = staffService.findByStoreId(storeId);

        return Response.ok(staff).build();
    }
}
