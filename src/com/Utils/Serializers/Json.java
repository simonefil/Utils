package com.Utils.Serializers;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Json {
    private static final Json DEFAULT_SERIALIZER;

    static {
        ObjectMapper mapper = new ObjectMapper();

        // Don't throw an exception when json has extra fields you are
        // not serializing on. This is useful when you want to use a pojo
        // for deserialization and only care about a portion of the json
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Ignore null values when writing json.
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        mapper.setSerializationInclusion(Include.NON_NULL);

        DEFAULT_SERIALIZER = new Json(mapper);
    }

    public static Json serializer() {
        return DEFAULT_SERIALIZER;
    }

    private final ObjectMapper mapper;
    private final ObjectWriter writer;
    private final ObjectWriter prettyWriter;

    // Only let this be called statically. Hide the constructor
    private Json(ObjectMapper parMapper) {
        this.mapper = parMapper;
        this.writer = parMapper.writer();
        this.prettyWriter = parMapper.writerWithDefaultPrettyPrinter();
    }

    public ObjectMapper mapper() {
        return mapper;
    }

    public ObjectWriter writer() {
        return writer;
    }

    public ObjectWriter prettyWriter() {
        return prettyWriter;
    }

    public <T> T fromJson(byte[] parBytes, Class<T> parClass) {
        try {
            return mapper.readValue(parBytes, parClass);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public <T> T fromJson(String parJson, Class<T> parClass) {
        try {
            return mapper.readValue(parJson, parClass);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public <T> T fromNode(JsonNode parNode, Class<T> parClass) {
        try {
            return mapper.readValue(parNode.toString(), parClass);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public <T> T fromObject(Object parObj, Class<T> parClass) {
        try {
            return mapper.readValue(toString(parObj), parClass);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public <T> T fromInputStream(InputStream parStream, Class<T> parClass) {
        try {
            return mapper.readValue(parStream, parClass);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public String toString(Object parObj) {
        try {
            return writer.writeValueAsString(parObj);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public String toPrettyString(Object parObj) {
        try {
            return prettyWriter.writeValueAsString(parObj);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public byte[] toByteArray(Object parObj) {
        try {
            return prettyWriter.writeValueAsBytes(parObj);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public JsonNode nodeFromJson(String parJson) {
        try {
            return mapper.readTree(parJson);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public JsonNode nodeFromObject(Object parObj) {
        try {
            return mapper.readTree(toString(parObj));
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static class JsonException extends RuntimeException {
        private JsonException(Exception ex) {
            super(ex);
        }
    }
}