package org.example.DesignPattern.Observer.Observer;

import org.example.DesignPattern.Observer.Observable.StockObservable;

public class EmailAlertObserverImpl implements NotificationAlertObserver{
    StockObservable observable;
    String email;
    public EmailAlertObserverImpl(String email,StockObservable observable) {
        this.email = email;
        this.observable= observable;
    }

    @Override
    public void update() {
        sendEmail(email,"Product is in stock");
    }

    void sendEmail(String email, String message){
        System.out.println("Email sent to: "+ email);
    }
}
