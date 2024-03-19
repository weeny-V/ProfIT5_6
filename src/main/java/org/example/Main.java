package org.example;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
        Class<Model> cls = Model.class;
        Path propertiesPath = Path.of(Creator.DEFAULT_RESOURCES_PATH + Creator.FILE_NAME);
        Model model = Creator.loadFromProperties(cls, propertiesPath);
    }
}