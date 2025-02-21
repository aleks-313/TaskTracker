package org.aleks;

import org.aleks.operations.ListOperation;
import org.aleks.operations.AddOperation;

public class TaskController {
    public static String jsonFile = "tasks.json";

    private final Function function;
    private final String argument;
    private final String additionalArgument;

    public TaskController(String[] strings) {
        CLParser parser = new CLParser(strings);

        function = parser.getFunction();
        argument = parser.getArgument();
        additionalArgument = parser.getAdditionalArgument();

        execute();
    }

    private void execute() {
        switch (function) {
            case ADD:
                new AddOperation(argument).execute();
                break;
            case UPDATE:
                //new UpdateOperation(argument, additionalArgument).execute();
                break;
            case DELETE:
                //new DeleteOperation(argument).execute();
                break;
            case LIST:
                new ListOperation(argument).execute();
                break;
            case MARK_DONE:
                break;
            case MARK_IN_PROGRESS:
                break;
        }
    }
}
