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
package com.vasslatam.sakila.services.type;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author diego
 */
public enum Rating {
    G("G"), PG("PG"), PG13("PG-13"), R("R"), NC17("NC-17");
    final private String value;
    final static private Map<String, Rating> values = new LinkedHashMap<>();

    static {
        for (Rating v : values()) {
            values.put(v.value, v);
        }
    }

    private Rating(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Rating findByValue(String value) {
        return values.get(value);
    }

}
