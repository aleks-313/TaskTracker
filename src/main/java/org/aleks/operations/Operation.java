package org.aleks.operations;

import org.aleks.JsonController;
import org.aleks.Task;
import org.aleks.TaskController;

import java.util.List;

public class Operation {
    final JsonController controller;
    List<Task> tasks;


    public Operation() {
        controller = new JsonController();
        controller.createFile(TaskController.jsonFile);
    }
}
