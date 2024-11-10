package com.lead.service.util.matchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.api.Assertions.assertThat;

public class ResponseBodyMatchers {
    private final ObjectMapper objectMapper;

    private ResponseBodyMatchers() {
        ObjectMapper timestampMapper = new ObjectMapper();
        timestampMapper.registerModule(new JavaTimeModule());
        this.objectMapper = timestampMapper;
    }

    public <T> ResultMatcher containsObjectAsJson(
            Object expectedObject,
            Class<T> targetClass) {
        return mvcResult -> {
            String json = mvcResult.getResponse().getContentAsString();
            T actualObject = objectMapper.readValue(json, targetClass);
            assertThat(actualObject).usingRecursiveComparison().isEqualTo(expectedObject);
        };
    }

    public static ResponseBodyMatchers responseBody() {
        return new ResponseBodyMatchers();
    }
}
