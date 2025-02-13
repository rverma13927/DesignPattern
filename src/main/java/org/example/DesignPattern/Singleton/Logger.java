package org.example.DesignPattern.Singleton;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    // Step 1: Private static instance of the class
    private static Logger instance;

    // Step 2: Private constructor to prevent instantiation from outside
    private Logger() {
        // Initialize the logger (e.g., open a log file)
        System.out.println("Logger instance created.");
    }

    // Step 3: Public static method to provide access to the instance
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) { // Thread-safe initialization
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    // Step 4: Method to log messages
    public void log(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String logMessage = "[" + timestamp + "] " + message;

        // Write the log message to a file (or console for simplicity)
        try (FileWriter writer = new FileWriter("application.log", true)) {
            writer.write(logMessage + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }

        // Print to console for demonstration
        System.out.println(logMessage);
    }

    // Example usage
    public static void main(String[] args) {
        // Get the singleton instance of Logger
        Logger logger = Logger.getInstance();

        // Log some messages
        logger.log("Application started.");
        logger.log("User logged in.");
        logger.log("Error: Invalid input detected.");
    }
}