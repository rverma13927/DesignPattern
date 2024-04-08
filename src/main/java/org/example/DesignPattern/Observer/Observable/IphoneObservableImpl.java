package org.example.DesignPattern.Observer.Observable;

import org.example.DesignPattern.Observer.Observer.NotificationAlertObserver;

import java.util.ArrayList;
import java.util.List;

public class IphoneObservableImpl implements StockObservable {


    /*
    * In real world example we can have a table notificationalert observer, where we will store the item,email,mobile
    * and insert when user call the notify api
    * and notify when owner update the stock
    *
    *
    * */
    List<NotificationAlertObserver> observerList = new ArrayList<>();
    public int stockCount =0;
    @Override
    public void add(NotificationAlertObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(NotificationAlertObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifySubscribers() {
            observerList.forEach(NotificationAlertObserver::update);
    }

    @Override
    public void setStockCount(int newStockAdded) {
            if(stockCount==0)notifySubscribers();
            this.stockCount = newStockAdded;
    }

    @Override
    public int getStockCount() {
        return this.stockCount;
    }
}
