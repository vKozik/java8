package com.grow.java8.calories.data;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateDeserializer extends StdDeserializer<LocalDateTime> {
    private static final long serialVersionUID = 1L;

    protected LocalDateDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        return LocalDateTime.parse(jp.readValueAs(String.class), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
