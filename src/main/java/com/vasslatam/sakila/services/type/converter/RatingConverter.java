/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasslatam.sakila.services.type.converter;

import com.vasslatam.sakila.services.type.Rating;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author diego
 */
@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {

    @Override
    public String convertToDatabaseColumn(Rating attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(String dbData) {
        return Rating.findByValue(dbData);
    }

}
