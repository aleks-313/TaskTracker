import org.aleks.JsonMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonMapperTest {
    final JsonMapper jsonMapper = new JsonMapper();
    final File file = new File(TaskControllerTest.nameAndExtensionOfReadOnlyFile);

    @Test
    void jsonMapperCanMapTasks(){
        try {
            jsonMapper.mapTasks(Files.readString(Path.of(file.getPath())));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
