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
package com.vasslatam.sakila.services.services;

import com.vasslatam.sakila.services.domain.Actor;
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

    public List<Film> findByActor(Actor actor) {
        return filmRepository.findByActor(actor);
    }

    //@TODO Busca de peliculas donde actua un actor en base al nombre. Pueden haber varios actores con el mismo nombre

    public List<Film> findByActors(List<Actor> actors) {
        return filmRepository.findByActors(actors);
    }
}
