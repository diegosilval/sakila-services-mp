/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasslatam.sakila.services.services;

import com.vasslatam.sakila.services.domain.Film;
import com.vasslatam.sakila.services.repositories.FilmRepository;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author diego
 */
@ApplicationScoped
public class FilmService {

    @Inject
    private FilmRepository filmRepository;

    public List<Film> findAll() {
        return filmRepository.findAll();
    }
}
