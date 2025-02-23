package org.aleks.operations;

public class DeleteOperation extends Operation {
    private int id = 0;

    public DeleteOperation(String id) {
        super();
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
        if (id > 0){
            controller.readTasks();
            tasks = controller.getTasks();
            tasks.removeIf(task -> task.getId() == id);
            controller.persistTasks(tasks);
        }

    }
}
