package org.aleks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.ArrayList;
import java.util.List;

public class JsonMapper {
    private final ObjectMapper objectMapper;

    public JsonMapper() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public List<Task> mapTasks(String json) {
        List<Task> tasks = new ArrayList<>();
        try {
            tasks = objectMapper.readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }
}
