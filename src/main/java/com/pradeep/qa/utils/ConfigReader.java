package com.pradeep.qa.utils;

import java.io.InputStream;
import java.util.Properties;

/** Reads settings from config.properties. A -D system property overrides the file, e.g. -Dbrowser=firefox */
public final class ConfigReader {

    private static final Properties PROPS = new Properties();

    static {
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in != null) {
                PROPS.load(in);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not load config.properties", e);
        }
    }

    private ConfigReader() { }

    public static String get(String key) {
        return System.getProperty(key, PROPS.getProperty(key));
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
