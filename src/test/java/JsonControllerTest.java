import org.aleks.JsonController;
import org.aleks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonControllerTest {
    JsonController jsonController;
    public static final String nameAndExtensionOfFile = "tasks-test.json";

    @BeforeEach
    void setup(){
        jsonController = new JsonController();
        jsonController.createFile(nameAndExtensionOfFile);
    }

    @Test
    void jsonControllerCreatesJsonFile(){
        jsonController.createFile(nameAndExtensionOfFile);
        File file = new File(nameAndExtensionOfFile);
        assertTrue(file.exists());
    }

    @Test
    void jsonControllerReadsTasks(){
        jsonController.readTasks();
        List<Task> tasks = jsonController.getTasks();
        assertEquals(3, tasks.size());
    }


}
