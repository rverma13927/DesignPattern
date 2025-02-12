package org.example.SolidPrinciple.openclose;

public class Notify {
}
//Without OCP (Violation):
class NotificationService1 {
    public void sendNotification(String type, String message) {
        if (type.equals("Email")) {
            System.out.println("Sending Email: " + message);
        } else if (type.equals("SMS")) {
            System.out.println("Sending SMS: " + message);
        } else if (type.equals("WhatsApp")) {
            System.out.println("Sending WhatsApp: " + message);
        }
    }
}

