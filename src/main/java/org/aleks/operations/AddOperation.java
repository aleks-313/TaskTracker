package org.aleks.operations;

import org.aleks.Status;
import org.aleks.Task;

import java.time.LocalDateTime;
import java.util.OptionalInt;

public class AddOperation extends Operation {
    private final String name;

    public AddOperation(String name) {
        super();
        this.name = name;
    }

    public void execute() {
        controller.readTasks();
        tasks = controller.getTasks();
        Task newTask = createNewTask();
        tasks.add(newTask);
        controller.persistTasks(tasks);
    }

    private Task createNewTask() {
        int nextID = calculateID();
        return new Task(nextID, name, Status.NOT_STARTED, LocalDateTime.now(), LocalDateTime.now());
    }

    private int calculateID() {
        OptionalInt nextID = tasks.stream()
                .mapToInt(Task::getId)
                .max();

        if (nextID.isPresent()) {
            return nextID.getAsInt() + 1;
        } else {
            return 1;
        }
    }
}
