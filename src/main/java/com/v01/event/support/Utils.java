package com.v01.event.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    //TODO 객체 → JSON 문자열
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.warn("JSON 직렬화 실패: {}",e.getMessage());
            return "json 직렬화 실패";
        }
    }

    //TODO  JSON 문자열 → 객체
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.warn("[Utils] JSON 역직렬화 실패: {}", e.getMessage());
            return null;
        }
    }

}
