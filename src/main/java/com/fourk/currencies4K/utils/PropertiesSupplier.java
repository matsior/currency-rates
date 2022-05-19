package com.fourk.currencies4K.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesSupplier {
    public static String getProperty(String key) {
        try (InputStream input = PropertiesSupplier.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
