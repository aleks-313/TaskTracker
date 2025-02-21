package org.aleks.operations;

import org.aleks.Status;
import org.aleks.Task;

import java.util.List;

public class ListOperation extends Operation{
    private final String status;

    public ListOperation(String status) {
        super();
        this.status = status;
    }

    public void execute() {
        controller.readTasks();

        if (null == status){
            tasks = controller.getTasks();
        } else if (status.equals("todo")){
            tasks = controller.getTasks().stream()
                    .filter(task -> task.getStatus().equals(Status.NOT_STARTED))
                    .toList();
        } else if (status.equals("in-progress")){
            tasks = controller.getTasks().stream()
                    .filter(task -> task.getStatus().equals(Status.IN_PROGRESS))
                    .toList();
        } else if (status.equals("done")){
            tasks = controller.getTasks().stream()
                    .filter(task -> task.getStatus().equals(Status.DONE))
                    .toList();
        } else {
            throw new IllegalArgumentException("Invalid argument: " + status);
        }

        listTasks();
    }

    private void listTasks() {
        for (Task task : tasks) {
            System.out.println("Task ID: " + task.getId());
            System.out.println("Task Description: " + task.getDescription());
            System.out.println("Task Status: " + task.getStatus());
            System.out.println("Created At: " + task.getCreatedAt());
            System.out.println("Updated At: " + task.getUpdatedAt());
            System.out.println("----------------------");
        }
    }
}
