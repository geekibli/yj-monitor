package com.yj.monitor.api.util;



import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Throwables;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class JsonUtil {
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T read(String json, Class<T> type) throws IOException {
        return objectMapper.readValue(json.getBytes(Charset.defaultCharset()), type);
    }

    public static <T> T safeRead(String json, Class<T> type) {
        try {
            return objectMapper.readValue(json.getBytes(Charset.defaultCharset()), type);
        } catch (IOException e) {
            throw propagate(e);
        }
    }


    public static String toJson(Object object) throws IOException {
        return objectMapper.writeValueAsString(object);
    }

    public static String safeToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw propagate(e);
        }
    }

    public static <T> List<T> safeReadList(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            throw propagate(e);
        }
    }

    public static ObjectNode createObjectNode() {
        return objectMapper.createObjectNode();
    }

    static RuntimeException propagate(Throwable throwable) {
        Throwables.throwIfUnchecked(throwable);
        throw new RuntimeException(throwable);
    }

}
