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
            if (file.createNewFile()){
                System.out.println("First boot up. Task list created. Welcome!");
            } else {
                System.out.println("Welcome back!");
            }
        } catch (IOException e) {
            System.out.println("File could not be created.");
        }
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
}
