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

import com.vasslatam.sakila.services.domain.Staff;
import com.vasslatam.sakila.services.domain.Store;
import com.vasslatam.sakila.services.repositories.StaffRepository;
import com.vasslatam.sakila.services.repositories.StoreRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Diego Silva Limaco <diego.silva at apuntesdejava.com>
 */
@ApplicationScoped
public class StaffService {

    @Inject
    private StoreRepository storeRepository;
    @Inject
    private StaffRepository staffRepository;

    public Staff findById(int id) {
        return staffRepository.findById(id);
    }

    public Staff findByStoreId(int storeId) {
        Store store=storeRepository.findById(storeId);
        if (store==null)return null;
        return staffRepository.findByStore(store);
    }
}
