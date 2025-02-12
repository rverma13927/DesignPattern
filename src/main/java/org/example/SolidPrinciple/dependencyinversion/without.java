package org.example.SolidPrinciple.dependencyinversion;


class EmailService {
    public void sendEmail(String message) {
        System.out.println("Email sent: " + message);
    }
}

class Notification {
    private EmailService emailService;

    public Notification() {
        this.emailService = new EmailService();
    }

    public void send(String message) {
        emailService.sendEmail(message);
    }
}

/**
 *
 * Problem:
 * Notification depends directly on EmailService (a low-level module).
 * If we need to send SMS or add other services, we need to modify the Notification class.
 * This violates DIP because the high-level module (Notification) depends on a low-level module (EmailService).
 *
 *
 */
public class without {
}
