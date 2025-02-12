package org.example.SolidPrinciple.dependencyinversion;


// Abstraction
interface MessageService {
    void sendMessage(String message);
}

// Low-level module 1
class EmailService1 implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Email sent: " + message);
    }
}

// Low-level module 2
class SMSService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("SMS sent: " + message);
    }
}

// High-level module depends on abstraction
class Notification1 {
    private MessageService messageService;

    public Notification1(MessageService messageService) {
        this.messageService = messageService;
    }

    public void notifyUser(String message) {
        messageService.sendMessage(message);
    }
}

 class Main {
    public static void main(String[] args) {
        MessageService emailService = new EmailService1();
        Notification1 notification1 = new Notification1(emailService);
        notification1.notifyUser("Hello via Email!");

        MessageService smsService = new SMSService();
        Notification1 notification2 = new Notification1(smsService);
        notification2.notifyUser("Hello via SMS!");
    }
}


public class with {
}
