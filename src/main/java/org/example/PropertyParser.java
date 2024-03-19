package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class PropertyParser {
    public void parseProp(Class<?> cls, Object obj, Path propertiesPath) throws IOException, IllegalAccessException {
        Properties properties = new Properties();

        properties.load(new FileInputStream(propertiesPath.toFile()));

        for(Field field : cls.getDeclaredFields()) {
            handleField(obj, field, properties);
        }
    }

    public void handleField(Object obj, Field field, Properties props) throws IllegalAccessException {
        String key, property;

        if (field.isAnnotationPresent(Property.class)) {
            Property annotation = field.getAnnotation(Property.class);
            key = !annotation.name().isEmpty() ? annotation.name() : field.getName();
        } else {
            key = field.getName();
        }

        property = props.getProperty(key);

        field.setAccessible(true);

        if(key.startsWith("string")) {
            field.set(obj, property);
        } else if(key.startsWith("number")) {
            field.setInt(obj, Integer.parseInt(property));
        } else if(key.startsWith("time")) {
            String format = field.getAnnotation(Property.class).format();
            if(format.isEmpty()) {
                throw new RuntimeException("Invalid date format");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDateTime date = LocalDateTime.parse(property, formatter);
            field.set(obj, date);
        }
    }
}
