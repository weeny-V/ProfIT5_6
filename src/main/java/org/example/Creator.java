package org.example;

import java.io.IOException;
import java.nio.file.Path;

public class Creator {
    public static final String DEFAULT_RESOURCES_PATH = "./src/main/resources/";
    public static final String FILE_NAME = "prop.properties";
    public static <T> T loadFromProperties(Class<T> cls, Path propertiesPath) throws IOException, InstantiationException, IllegalAccessException {
        T model = createObjByClass(cls);

        PropertyParser analyzer = new PropertyParser();
        analyzer.parseProp(cls, model,propertiesPath);

        return model;
    }

    private static  <T>T createObjByClass(Class<T> cls) throws RuntimeException, InstantiationException, IllegalAccessException {
        return cls.newInstance();

    }
}
