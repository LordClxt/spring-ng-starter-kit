package com.backend.app.common.converter;

import java.lang.reflect.Field;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MapConverter {

    private final ObjectMapper objectMapper = new ObjectMapper(); 
    
    // public Map getJsonPropertyMap(String property) throws NoSuchFieldException, IllegalAccessException{
    //     Field field = this.getClass().getField(property);
    //     field.get(this);
    //     return this.objectMapper.readValue();
    // }
}
