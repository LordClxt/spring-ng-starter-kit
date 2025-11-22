package com.backend.app.common.converter;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MapConverter {

    private final ObjectMapper objectMapper = new ObjectMapper(); 
    
    public Map<String, Object> getJsonPropertyMap(String property) throws NoSuchFieldException, IllegalAccessException, JsonProcessingException, IllegalArgumentException{
        Field field = this.getClass().getField(property);
        field.setAccessible(true);
        return this.objectMapper.readValue(
                (String) Optional.ofNullable(field.get(this)).orElse("{}"),
                new TypeReference<Map<String, Object>>() {}
        );
    }
}
