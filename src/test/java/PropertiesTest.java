import org.example.Creator;
import org.example.Model;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropertiesTest {
    private final static String sourceProperties = "./src/main/resources/prop.properties";
    private static final Model resultModel;

    static {
        resultModel = new Model();
        resultModel.setStringProperty("value1");
        resultModel.setMyNumber(10);
        resultModel.setTimeProperty(LocalDateTime.of(2022, 11, 29, 18, 30));
    }

    @Test
    public void testLoadProperties() throws IOException, InstantiationException, IllegalAccessException {
        Model model = Creator.loadFromProperties(Model.class, Path.of(sourceProperties));

        assertEquals(resultModel, model);
    }
}
