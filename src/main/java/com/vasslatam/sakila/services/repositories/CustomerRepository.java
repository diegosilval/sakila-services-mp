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

import com.vasslatam.sakila.services.domain.Address_;
import com.vasslatam.sakila.services.domain.City_;
import com.vasslatam.sakila.services.domain.Customer;
import com.vasslatam.sakila.services.domain.Customer_;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Diego Silva Limaco <diego.silva at apuntesdejava.com>
 */
@ApplicationScoped
public class CustomerRepository {

    @Inject
    private EntityManager em;

    public List<Customer> findByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> customer = cq.from(Customer.class);
        String[] hints = (name == null ? "" : name).split(" ");
        cq.select(customer);
        List<Predicate> where = new ArrayList<>();
        for (String hint : hints) {
            String w = '%' + hint.replace(' ', '%') + '%';
            where.add(
                    cb.like(customer.get(Customer_.firstName), w));
            where.add(
                    cb.like(customer.get(Customer_.lastName), w)
            );
        }
        cq.where(
                cb.or(
                        where.toArray(new Predicate[0])
                )
        );
        customer.fetch(Customer_.address, JoinType.LEFT)
                .fetch(Address_.city, JoinType.LEFT)
                .fetch(City_.country, JoinType.LEFT);
        return em.createQuery(cq).getResultList();
    }
}
