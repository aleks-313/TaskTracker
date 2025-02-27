package org.aleks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonController {
    final private JsonMapper jsonMapper = new JsonMapper();
    private List<Task> tasks = new ArrayList<>();
    private File file;

    public void createFile(String fileNameAndExtension){
        file = new File(fileNameAndExtension);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("File could not be created.");
        }
        jsonMapper.setJsonFile(file);
    }

    public void readTasks(){
        try {
            tasks = jsonMapper.mapTasks(Files.readString(Path.of(file.getPath())));
        } catch (IOException e) {
            System.out.println("File could not be read.");
        }
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public void persistTasks(List<Task> newTasks){
        jsonMapper.persistTasks(newTasks);
    }
}
