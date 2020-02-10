/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasslatam.sakila.services.type.converter;

import com.vasslatam.sakila.services.type.SpecialFeature;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author diego
 */
@Converter
public class SpecialFeaturesList implements AttributeConverter<List<SpecialFeature>, String> {

    @Override
    public String convertToDatabaseColumn(List<SpecialFeature> attribute) {
        if (attribute == null) {
            return null;
        }
        String result = attribute.stream().map((item) -> item.getValue()).collect(Collectors.joining(","));
        return result;
    }

    @Override
    public List<SpecialFeature> convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        List<String> column = Arrays.asList(dbData.split(","));
        return column.stream().map((m) -> SpecialFeature.findByValue(m)).collect(Collectors.toList());
    }

}
