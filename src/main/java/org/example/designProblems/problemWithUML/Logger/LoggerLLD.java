package org.example.designProblems.problemWithUML.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
        import java.io.*;

// Enums
 enum LogLevel {
    DEBUG, INFO, WARN, ERROR
}
public class LoggerLLD {
}
// Logger Interface
 interface Logger {
    void log(LogLevel level, String message);
    void debug(String message);
    void info(String message);
    void warn(String message);
    void error(String message);
}

// Base Logger Implementation
 class BaseLogger implements Logger {
    private final LogLevel logLevel;
    private final LogFormatter formatter;
    private final List<LogAppender> appenders;

    public BaseLogger(LogLevel logLevel, LogFormatter formatter, List<LogAppender> appenders) {
        this.logLevel = logLevel;
        this.formatter = formatter;
        this.appenders = Collections.unmodifiableList(new ArrayList<>(appenders));
    }

    @Override
    public void log(LogLevel level, String message) {
        if (level.ordinal() >= logLevel.ordinal()) {
            String formattedMessage = formatter.format(level, message);
            for (LogAppender appender : appenders) {
                appender.append(formattedMessage);
            }
        }
    }

    @Override
    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    @Override
    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    @Override
    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    @Override
    public void error(String message) {
        log(LogLevel.ERROR, message);
    }
}

// Formatter Interface and Implementations
 interface LogFormatter {
    String format(LogLevel level, String message);
}

 class SimpleFormatter implements LogFormatter {
    @Override
    public String format(LogLevel level, String message) {
        return String.format("[%s] %s: %s",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                level,
                message);
    }
}

 class JsonFormatter implements LogFormatter {
    @Override
    public String format(LogLevel level, String message) {
        return String.format("{\"timestamp\":\"%s\",\"level\":\"%s\",\"message\":\"%s\"}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                level,
                message.replace("\"", "\\\""));
    }
}

// Appender Interface and Implementations
 interface LogAppender {
    void append(String logMessage);
}

 class ConsoleAppender implements LogAppender {
    @Override
    public void append(String logMessage) {
        System.out.println(logMessage);
    }
}

 class FileAppender implements LogAppender {
    private final String filePath;

    public FileAppender(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public synchronized void append(String logMessage) {
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(logMessage);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}

// Logger Factory
 final class LoggerFactory {
    private LoggerFactory() {} // Prevent instantiation

    public static Logger createConsoleLogger(LogLevel level, LogFormatter formatter) {
        return new BaseLogger(level, formatter, List.of(new ConsoleAppender()));
    }

    public static Logger createFileLogger(LogLevel level, LogFormatter formatter, String filePath) {
        return new BaseLogger(level, formatter, List.of(new FileAppender(filePath)));
    }

    public static Logger createMultiAppenderLogger(LogLevel level, LogFormatter formatter, LogAppender... appenders) {
        return new BaseLogger(level, formatter, List.of(appenders));
    }
}

// Example Usage
 class LoggingExample {
    public static void main(String[] args) {
        // Simple console logger
        Logger consoleLogger = LoggerFactory.createConsoleLogger(LogLevel.INFO, new SimpleFormatter());
        consoleLogger.error("This is an info message");

        // File logger with JSON formatting
        Logger fileLogger = LoggerFactory.createFileLogger(
                LogLevel.DEBUG,
                new JsonFormatter(),
                "application.log"
        );
        fileLogger.debug("Debugging information");

        // Logger with multiple appenders
        Logger multiLogger = LoggerFactory.createMultiAppenderLogger(
                LogLevel.WARN,
                new SimpleFormatter(),
                new ConsoleAppender(),
                new FileAppender("errors.log")
        );
        multiLogger.error("Critical error occurred");

        // We created factory so that we dont have to write such code
        SimpleFormatter formatter = new SimpleFormatter();
        BaseLogger baseLogger = new BaseLogger(LogLevel.DEBUG,formatter,List.of(new ConsoleAppender()));
        baseLogger.info("dsfdsfsf");
    }
}