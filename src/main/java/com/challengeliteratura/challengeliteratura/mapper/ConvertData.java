package com.challengeliteratura.challengeliteratura.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ConvertData implements IConvertData {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T getInformationAPI(String json, Class<T> classed) {
        try {
            return objectMapper.readValue(json,classed);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}