/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasslatam.sakila.services.repositories;

import com.vasslatam.sakila.services.domain.Film;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author diego
 */
@ApplicationScoped
public class FilmRepository {

    @Inject
    private EntityManager em;

    public List<Film> findAll() {
        return em.createQuery("select f from Film f", Film.class).getResultList();
    }
}
