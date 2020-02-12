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
package com.vasslatam.sakila.services.repositories;

import com.vasslatam.sakila.services.domain.Actor;
import com.vasslatam.sakila.services.domain.Film;
import com.vasslatam.sakila.services.domain.FilmActor;
import com.vasslatam.sakila.services.domain.FilmActor_;
import com.vasslatam.sakila.services.domain.Film_;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

    public List<Film> findFilmByTitle(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Film> cq = cb.createQuery(Film.class);
        Root<Film> film = cq.from(Film.class);
        cq.select(film).where(
                cb.like(film.get(Film_.title), name.replaceAll(" ", "%"))
        );
        TypedQuery<Film> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Film> findByActor(Actor actor) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Film> cq = cb.createQuery(Film.class);
        Root<FilmActor> filmActor = cq.from(FilmActor.class);
        cq.select(filmActor.get(FilmActor_.film))
                .where(
                        cb.equal(filmActor.get(FilmActor_.actor), actor)
                );
        TypedQuery<Film> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Film> findByActors(List<Actor> actors) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Film> cq = cb.createQuery(Film.class);
        Root<FilmActor> filmActor = cq.from(FilmActor.class);
        cq.select(filmActor.get(FilmActor_.film))
                .where(
                        filmActor.get(FilmActor_.actor).in(actors)
                );
        TypedQuery<Film> query = em.createQuery(cq);
        return query.getResultList();
    }
}
