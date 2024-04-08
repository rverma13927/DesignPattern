package org.example.DesignPattern.Observer;

import org.example.DesignPattern.Observer.Observable.IphoneObservableImpl;
import org.example.DesignPattern.Observer.Observable.StockObservable;
import org.example.DesignPattern.Observer.Observer.EmailAlertObserverImpl;
import org.example.DesignPattern.Observer.Observer.NotificationAlertObserver;

public class Main {
    public static void main(String[] args) {
        StockObservable stockObservable = new IphoneObservableImpl();
        NotificationAlertObserver notificationAlertObserver= new EmailAlertObserverImpl("xyz@gmail.com",stockObservable);
        NotificationAlertObserver notificationAlertObserver1= new EmailAlertObserverImpl("abc@gmail.com",stockObservable);
        NotificationAlertObserver notificationAlertObserver2= new EmailAlertObserverImpl("mno@gmail.com",stockObservable);

        stockObservable.add(notificationAlertObserver);
        stockObservable.add(notificationAlertObserver1);
        stockObservable.add(notificationAlertObserver2);

        stockObservable.setStockCount(10);

    }
}
