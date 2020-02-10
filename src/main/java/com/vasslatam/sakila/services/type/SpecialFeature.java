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
