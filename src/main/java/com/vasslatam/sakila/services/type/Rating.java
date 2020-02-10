/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
