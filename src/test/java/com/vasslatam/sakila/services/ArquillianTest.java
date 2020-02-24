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
package com.vasslatam.sakila.services;

import com.vasslatam.sakila.services.domain.Actor;
import com.vasslatam.sakila.services.domain.Customer;
import com.vasslatam.sakila.services.domain.Film;
import com.vasslatam.sakila.services.domain.Staff;
import com.vasslatam.sakila.services.domain.Store;
import com.vasslatam.sakila.services.endpoints.StaffEndpoint;
import com.vasslatam.sakila.services.repositories.ActorRepository;
import com.vasslatam.sakila.services.repositories.JPAProvider;
import com.vasslatam.sakila.services.repositories.StaffRepository;
import com.vasslatam.sakila.services.services.CustomerService;
import com.vasslatam.sakila.services.services.StaffService;
import com.vasslatam.sakila.services.type.Rating;
import com.vasslatam.sakila.util.DataSourceProvider;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ApplyScriptAfter;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Diego Silva Limaco <diego.silva at apuntesdejava.com>
 */
@RunWith(Arquillian.class)
public class ArquillianTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArquillianTest.class);

    @Inject
    private ActorRepository actorRepository;

    @Inject
    private CustomerService customerService;

    @ArquillianResource
    private URL deploymentURL;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "sakila.war")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addClasses(
                        DataSourceProvider.class,
                        JPAProvider.class,
                        JAXRSConfiguration.class,
                        StaffEndpoint.class,
                        StaffService.class,
                        StaffRepository.class,
                        Staff.class
                )
                .addPackage(ActorRepository.class.getPackage())
                .addPackage(CustomerService.class.getPackage())
                .addPackage(StaffEndpoint.class.getPackage())
                .addPackage(Actor.class.getPackage())
                .addPackages(true, Rating.class.getPackage());
    }

    @Test
    @UsingDataSet("datasets/actors.json")
    public void findActor() {
        LOGGER.info("----- PROBANDO BUSQUEDA DE ACTORES -----");
        List<Actor> actors = actorRepository.findByName("JHON");
        assertFalse(actors.isEmpty());
        LOGGER.info("total actores:{}", actors.size());
    }

    @Test
    @UsingDataSet("datasets/customers.json")
    public void findCustomer() {
        LOGGER.info("----- PROBANDO BUSQUEDA DE CLIENTE -----");
        List<Customer> customers = customerService.findByName("Albert Tesla");
        assertFalse(customers.isEmpty());
        LOGGER.info("total customers:{}", customers.size());
    }

    private WebTarget createClient() throws URISyntaxException {
        LOGGER.info("url:{}", deploymentURL.toURI());
        Client client = ClientBuilder
                .newBuilder()
                .register(JacksonJaxbJsonProvider.class)
                .build();
        return client.target(deploymentURL.toURI())
                .path("resources");
    }

    @Test
    @UsingDataSet("datasets/rent_dvd_data.json")
    @ApplyScriptAfter("scripts/delete_rent_dvd_data.sql")
    public void testRentDVD() throws URISyntaxException {
        LOGGER.info("----- ALQUILER DE DVD -----");
        try ( Response respStores = createClient().path("store").request().get()) {
            assertEquals(Response.Status.OK.getStatusCode(), respStores.getStatus());
            List<Store> stores = respStores.readEntity(new GenericType<List<Store>>() {
            });
            String storeId = String.valueOf(stores.get(0).getStoreId());
            try ( Response respStaff = createClient().path("staff").path("store").path(storeId).request().get()) {

                assertEquals(Response.Status.OK.getStatusCode(), respStaff.getStatus());
                Staff staff = respStaff.readEntity(Staff.class);

                LOGGER.info("staff name:{} {}  ", staff.getFirstName(), staff.getLastName());

                try ( Response repsFilm = createClient().path("film").path("store").path(storeId).request().get()) {

                    assertEquals(Response.Status.OK.getStatusCode(), repsFilm.getStatus());
                    List<Film> films = repsFilm.readEntity(new GenericType<List<Film>>() {
                    });
                    films.forEach((f) -> LOGGER.info("-->{}", f.getTitle()));
                }
            }

        }

    }

}
