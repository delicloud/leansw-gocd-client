package com.thoughtworks.lean.gocd.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JSONUtil {
    static ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONUtil.class.getSimpleName());

    public static String toJson(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> T parseJSON(String string) throws IOException {
        return objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(string, new TypeReference<T>() {
        });
    }

    public static <T extends Object> T parseJSON(String string, Class<T> tClass) throws IOException {
        return objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(string, tClass);
    }


    public static <T extends Object> T parseJSON(String string, TypeReference<T> tTypeReference) throws IOException {
        return objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(string, tTypeReference);
    }

}