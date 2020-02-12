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
package com.vasslatam.sakila.util;

import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

/**
 *
 * @author Diego Silva Limaco <diego.silva at apuntesdejava.com>
 */
@ApplicationScoped
@DataSourceDefinition(
        className = "org.h2.Driver",
        name = "java:global/SakilaDataSource",
        url = "jdbc:h2:mem:",
        user = "sa",
        password = "",
        maxPoolSize = 5,
        minPoolSize = 1,
        initialPoolSize = 1,
        properties = {
            "fish.payara.log-jdbc-calls=true"
        }
)
public class DataSourceProvider {

    @Resource(lookup = "java:global/SakilaDataSource")
    @Produces
    private DataSource dataSource;
}
