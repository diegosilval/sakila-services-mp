/*
 * Copyright 2020 diego.
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
import com.vasslatam.sakila.services.domain.Actor_;
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
public class ActorRepository {

    @Inject
    private EntityManager em;

    public List<Actor> findByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Actor> cq = cb.createQuery(Actor.class);
        Root<Actor> actor = cq.from(Actor.class);
        String nameWildcard = '%' + name.replace(' ', '%') + '%';
        cq.select(actor)
                .where(
                        cb.or(
                                cb.like(actor.get(Actor_.firstName), nameWildcard),
                                cb.like(actor.get(Actor_.lastName), nameWildcard)
                        )
                );
        TypedQuery<Actor> query = em.createQuery(cq);
        return query.getResultList();
    }
}
