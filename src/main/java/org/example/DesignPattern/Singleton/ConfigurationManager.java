package org.example.DesignPattern.Singleton;

import java.io.File;
import java.util.Properties;

/**
 * In many applications, configuration settings (e.g., database URLs, API keys) are loaded from a file or
 * environment variables. A Singleton can ensure that these settings are loaded once and shared across the application.
 *
 * Interview Question:
 * "Implement a Configuration Manager using the Singleton pattern. The manager should load configuration settings from
 * a file and provide access to them."
 *
 * https://www.geeksforgeeks.org/java-util-properties-class-java/
 *
 */
import java.io.FileInputStream;
import java.io.IOException;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private Properties properties;

    private ConfigurationManager() {
        properties = new Properties();
        loadConfiguration();
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    private void loadConfiguration() {

        try (FileInputStream fis = new FileInputStream("src/main/java/org.example/DesignPattern/Singleton/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        ConfigurationManager configManager = ConfigurationManager.getInstance();
        String dbUrl = configManager.getProperty("database.url");
        String apiKey = configManager.getProperty("api.key");

        System.out.println("Database URL: " + dbUrl);
        System.out.println("API Key: " + apiKey);
    }
}
