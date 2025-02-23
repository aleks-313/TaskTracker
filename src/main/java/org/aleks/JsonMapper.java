package org.aleks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonMapper {
    private final ObjectMapper objectMapper;
    private File jsonFile;

    public JsonMapper() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public List<Task> mapTasks(String json) {
        List<Task> tasks = new ArrayList<>();
        try {
            tasks = objectMapper.readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            System.out.println("Could not read tasks. Maybe you haven't added any?");
        }
        return tasks;
    }

    public void persistTasks(List<Task> tasks) {
        try{
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void setJsonFile(File jsonFile) {
        this.jsonFile = jsonFile;
    }
}
