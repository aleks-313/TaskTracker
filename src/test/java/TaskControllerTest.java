import org.aleks.JsonController;
import org.aleks.Status;
import org.aleks.TaskController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class TaskControllerTest {
    public static final String nameAndExtensionOfReadOnlyFile = "tasks-test.json";
    public static final String nameAndExtensionOfWriteFile = "tasks-test-2.json";

    @BeforeEach
    public void setUp() {
        TaskController.jsonFile = nameAndExtensionOfWriteFile;
        File jsonFile = new File(nameAndExtensionOfWriteFile);
        jsonFile.delete();
    }

    @Nested
    class ListOperationTests{

        @Test
        public void taskControllerCanExecuteListAllFunction() {
            new TaskController(new String[] {"list"});
        }

        @Test
        public void taskControllerCanExecuteListTodoFunction() {
            new TaskController(new String[] {"list", "todo"});
        }

        @Test
        public void taskControllerCanExecuteListInProgressFunction() {
            new TaskController(new String[] {"list", "in-progress"});

        }

        @Test
        public void taskControllerCanExecuteListDoneFunction() {
            new TaskController(new String[] {"list", "done"});
        }

        @Test
        public void taskControllerThrowsExceptionAtIllegalListArgument() {
            assertThrows(IllegalArgumentException.class, () -> new TaskController(new String[] {"list", "illegal"}));
        }
    }

    @Nested
    class AddOperationTests{
        @Test
        public void taskControllerCanExecuteAddFunction() {
            new TaskController(new String[] {"add", "Learn Python"});
        }

        @Test
        public void taskControllerCanAppendTasksWhenAdding() {
            new TaskController(new String[] {"add", "Learn Python"});
            new TaskController(new String[] {"add", "Learn Java"});

            JsonController jsonController = new JsonController();
            jsonController.createFile(TaskControllerTest.nameAndExtensionOfWriteFile);
            jsonController.readTasks();

            assertEquals(2, jsonController.getTasks().size());
        }
    }

    @Nested
    class DeleteOperationTests{
        @Test
        public void taskControllerCanExecuteDeleteFunction() {
            new TaskController(new String[] {"add", "Learn Python"});
            new TaskController(new String[] {"add", "Learn Java"});
            new TaskController(new String[] {"delete", "1"});

            JsonController jsonController = new JsonController();
            jsonController.createFile(nameAndExtensionOfWriteFile);
            jsonController.readTasks();

            assertEquals(1, jsonController.getTasks().size());
        }
    }

    @Nested
    class MarkOperationTests{
        @Test
        public void taskControllerCanExecuteMarkInProgressFunction() {
            new TaskController(new String[] {"add", "Learn Python"});
            new TaskController(new String[] {"mark_in_progress", "1"});

            JsonController jsonController = new JsonController();
            jsonController.createFile(nameAndExtensionOfWriteFile);
            jsonController.readTasks();

            assertEquals(1, jsonController.getTasks().size());
            assertEquals(Status.IN_PROGRESS, jsonController.getTasks().getFirst().getStatus());
        }

        @Test
        public void taskControllerCanExecuteMarkDoneFunction() {
            new TaskController(new String[] {"add", "Learn Python"});
            new TaskController(new String[] {"mark_done", "1"});

            JsonController jsonController = new JsonController();
            jsonController.createFile(nameAndExtensionOfWriteFile);
            jsonController.readTasks();

            assertEquals(1, jsonController.getTasks().size());
            assertEquals(Status.DONE, jsonController.getTasks().getFirst().getStatus());
        }
    }

    @Nested
    class UpdateOperationTests{
        @Test
        public void taskControllerCanExecuteUpdateFunction() {
            new TaskController(new String[] {"add", "Learn Python"});
            new TaskController(new String[] {"update", "1", "Learn Java"});

            JsonController jsonController = new JsonController();
            jsonController.createFile(nameAndExtensionOfWriteFile);
            jsonController.readTasks();

            assertEquals("Learn Java", jsonController.getTasks().getFirst().getDescription());
        }
    }
}
