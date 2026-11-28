package com.wrinklewars.config;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JacksonConfigurationTest {

    @Test
    void serializesAndReadsJson() throws Exception {
        var mapper = new JacksonConfiguration().objectMapper();
        var json = mapper.writeValueAsString(Map.of("status", "ok"));
        assertEquals("ok", mapper.readTree(json).get("status").asText());
    }
}
