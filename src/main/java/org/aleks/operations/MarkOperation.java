package org.aleks.operations;

import org.aleks.Status;
import org.aleks.Task;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.OptionalInt;

public class MarkOperation extends Operation {
    private final Status status;
    private int id;

    public MarkOperation(Status status, String id) {
        super();
        this.status = status;
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
        changeStatus();
        controller.persistTasks(tasks);
    }

    private void changeStatus() {
        Optional<Task> taskToChange = tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst();

        if (taskToChange.isPresent()) {
            taskToChange.get().setStatus(status);
        } else {
            System.out.println("No task found with id " + id);
        }
    }

}
