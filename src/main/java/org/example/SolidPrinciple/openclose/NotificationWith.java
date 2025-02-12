package org.example.SolidPrinciple.openclose;

public class NotificationWith {
}
//No existing code is modified. Just create a new class and pass it to the NotificationService.


// Step 1: Create an interface for Notification
 interface Notification {
    void send(String message);
}

// Step 2: Implement notification types
 class EmailNotification implements Notification {
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}

 class SMSNotification implements Notification {
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

 class WhatsAppNotification implements Notification {
    public void send(String message) {
        System.out.println("Sending WhatsApp: " + message);
    }
}

// Step 3: NotificationService uses Notification interface
 class NotificationService {
    public void sendNotification(Notification notification, String message) {
        notification.send(message);
    }
}




// Step 1: Logger interface
 interface Logger {
    void log(String message);
}

// Step 2: Implement different loggers
 class ConsoleLogger implements Logger {
    public void log(String message) {
        System.out.println("Console Logger: " + message);
    }
}

 class FileLogger implements Logger {
    public void log(String message) {
        System.out.println("File Logger: " + message);
    }
}

// Step 3: LoggerService uses Logger interface
 class LoggerService {
    public void logMessage(Logger logger, String message) {
        logger.log(message);
    }
}

// Adding a new logger like DatabaseLogger
 class DatabaseLogger implements Logger {
    public void log(String message) {
        System.out.println("Database Logger: " + message);
    }
}


