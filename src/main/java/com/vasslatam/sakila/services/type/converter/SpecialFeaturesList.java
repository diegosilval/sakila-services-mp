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
