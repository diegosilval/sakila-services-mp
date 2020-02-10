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
public enum SpecialFeature {
    TRAILERS("Trailers"), COMMENTARIES("Commentaries"), DELETED_SCENES("Deleted Scenes"), BEHIND_SCENES("Behind the Scenes");
    private final String value;
    private final static Map<String, SpecialFeature> values = new LinkedHashMap<>();

    static {
        for (SpecialFeature v : values()) {
            values.put(v.value, v);
        }
    }

    private SpecialFeature(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SpecialFeature findByValue(String value) {
        return values.get(value);
    }

}
