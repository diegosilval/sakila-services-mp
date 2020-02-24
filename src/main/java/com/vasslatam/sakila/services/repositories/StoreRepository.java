/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasslatam.sakila.services.repositories;

import com.vasslatam.sakila.services.domain.Store;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author Diego Silva Limaco <diego.silva at apuntesdejava.com>
 */
@ApplicationScoped
public class StoreRepository {

    @Inject
    private EntityManager em;

    public List<Store> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        return em.createQuery(cb.createQuery(Store.class)).getResultList();
    }

    public Store findById(int storeId) {
        return em.find(Store.class, storeId);
    }

}
