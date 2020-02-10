/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasslatam.sakila.services.repositories;

import com.vasslatam.sakila.services.domain.Country;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author diego
 */
@ApplicationScoped
public class CountryRepository {

    @Inject
    private EntityManager em;

    public List<Country> findAll() {
        TypedQuery<Country> query = em.createQuery(" select c from Country c", Country.class);
        return query.getResultList();
    }
}
