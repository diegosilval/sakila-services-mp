/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasslatam.sakila.services.services;

import com.vasslatam.sakila.services.domain.Country;
import com.vasslatam.sakila.services.repositories.CountryRepository;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author diego
 */
@ApplicationScoped
public class CountryService {

    @Inject
    private CountryRepository countryRepository;

    public List<Country> findAll() {
        return countryRepository.findAll();
    }
}
