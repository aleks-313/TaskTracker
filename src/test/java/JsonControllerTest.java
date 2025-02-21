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

    @BeforeEach
    void setup(){
        jsonController = new JsonController();
        jsonController.createFile(TaskControllerTest.nameAndExtensionOfReadOnlyFile);
    }

    @Test
    void jsonControllerCreatesJsonFile(){
        File file = new File(TaskControllerTest.nameAndExtensionOfWriteFile);
        if (!file.delete()){
            System.out.println("Failed to delete file " + file.getAbsolutePath() + ". The file might not exist");
        }

        jsonController.createFile(TaskControllerTest.nameAndExtensionOfWriteFile);

        assertTrue(file.exists());
    }

    @Test
    void jsonControllerReadsTasks(){
        jsonController.readTasks();
        List<Task> tasks = jsonController.getTasks();
        assertEquals(3, tasks.size());
    }


}
