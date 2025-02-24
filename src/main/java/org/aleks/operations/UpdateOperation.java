package org.aleks.operations;

import org.aleks.Task;

import java.time.LocalDateTime;
import java.util.Optional;

public class UpdateOperation extends Operation {
    private final String name;
    private int id;

    public UpdateOperation(String id, String name) {
        super();
        this.name = name;
        validateInputAndSave(id);
    }

    private void validateInputAndSave(String id) {
        try {
            this.id = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            System.out.println("Task ID expected");
        }

        if (this.id <= 0){
            System.out.println("Task ID should be a positive integer");
        }
    }

    public void execute() {
        controller.readTasks();
        tasks = controller.getTasks();
        changeName();
        controller.persistTasks(tasks);
    }

    private void changeName() {
        Optional<Task> taskToChange = tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst();

        if (taskToChange.isPresent()) {
            taskToChange.get().setDescription(name);
            taskToChange.get().setUpdatedAt(LocalDateTime.now());
        } else {
            System.out.println("No task found with id " + id);
        }
    }

}
