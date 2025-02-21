import org.aleks.JsonController;
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
    }

    @Nested
    class ListOperationTests{

        @Test
        public void taskControllerCanExecuteListAllFunction() {
            TaskController controller = new TaskController(new String[] {"list"});
        }

        @Test
        public void taskControllerCanExecuteListTodoFunction() {
            TaskController controller = new TaskController(new String[] {"list", "todo"});
        }

        @Test
        public void taskControllerCanExecuteListInProgressFunction() {
            TaskController controller = new TaskController(new String[] {"list", "in-progress"});
        }

        @Test
        public void taskControllerCanExecuteListDoneFunction() {
            TaskController controller = new TaskController(new String[] {"list", "done"});
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
            TaskController controller = new TaskController(new String[] {"add", "Learn Python"});
        }

        @Test
        public void taskControllerCanAppendTasksWhenAdding() {
            File jsonFile = new File(nameAndExtensionOfWriteFile);
            jsonFile.delete();

            TaskController controller = new TaskController(new String[] {"add", "Learn Python"});
            controller = new TaskController(new String[] {"add", "Learn Java"});

            JsonController jsonController = new JsonController();
            jsonController.createFile(TaskControllerTest.nameAndExtensionOfWriteFile);
            jsonController.readTasks();

            assertEquals(2, jsonController.getTasks().size());
        }
    }
}
