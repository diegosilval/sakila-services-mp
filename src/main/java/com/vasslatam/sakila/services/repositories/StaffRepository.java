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

import com.vasslatam.sakila.services.domain.Staff;
import com.vasslatam.sakila.services.domain.Staff_;
import com.vasslatam.sakila.services.domain.Store;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Diego Silva Limaco <diego.silva at apuntesdejava.com>
 */
@ApplicationScoped
public class StaffRepository {

    @Inject
    private EntityManager em;

    public Staff findById(Integer id) {
        return em.find(Staff.class, id);
    }

    public Staff findByStore(Store store) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Staff> cq = cb.createQuery(Staff.class);
        Root<Staff> staff = cq.from(Staff.class);
        cq.select(staff)
                .where(cb.equal(staff.get(Staff_.store), store));
        List<Staff> result = em.createQuery(cq).getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }
}
