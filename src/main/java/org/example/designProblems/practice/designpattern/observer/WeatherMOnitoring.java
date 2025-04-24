package org.example.designProblems.practice.designpattern.observer;


/*
 *You are building a weather monitoring system for a weather station. The system needs to track and report changes in
 *weather data like temperature, humidity, and pressure. Various applications
 *(like mobile apps, display boards, or websites) will be interested in receiving updates when weather data changes.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantReadWriteLock;

///  subject-- observable , observer
///
///
interface Observer{
    void display();
}
abstract class WeatherStation {
    protected double temperature;
    protected double humidity;
    protected double pressure;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    abstract void addObserver(Observer observer);
    abstract void removeObserver(Observer observer);
    abstract void notifyObservers();

    // Getters and Setters for instance variables
    public double getTemperature() {
        lock.readLock().lock();
        try {
            return temperature;
        } finally {
            lock.readLock().unlock();
        }
    }

    public double getHumidity() {
        lock.readLock().lock();
        try {
            return humidity;
        } finally {
            lock.readLock().unlock();
        }
    }

    public double getPressure() {
        lock.readLock().lock();
        try {
            return pressure;
        } finally {
            lock.readLock().unlock();
        }
    }
    // same way we can remove syn and use lock
    public synchronized void setTemperature(double temperature) {
        this.temperature = temperature;
        notifyObservers();  // Notify observers when data changes
    }

    public synchronized void setHumidity(double humidity) {
        lock.writeLock().lock();
        try {
            this.humidity = humidity;
        } finally {
            lock.writeLock().unlock();
        }
        notifyObservers();// Notify observers when data changes
    }

    public synchronized void setPressure(double pressure) {
        this.pressure = pressure;
        notifyObservers();  // Notify observers when data changes
    }
}


 class ConcreteWeatherStation extends WeatherStation{
        //Collections.synchronizedList(new ArrayList<>())


     List<Observer> observerList = new CopyOnWriteArrayList<>();
     ExecutorService executorService = Executors.newFixedThreadPool(10);

     @Override
     synchronized void addObserver(Observer observer) {
        observerList.add(observer);
     }

     @Override
     synchronized void removeObserver(Observer observer) {
        observerList.remove(observer);
     }


     @Override
     synchronized void notifyObservers() {
         for (Observer observer : observerList) {
             executorService.submit(() -> observer.display());  // Offload to a separate thread
         }
     }
     public void shutdown() {
         executorService.shutdown();
     }
 }


class MobileDisplay implements Observer{
    WeatherStation weatherStation;

    public MobileDisplay(WeatherStation weatherStation) {
        this.weatherStation = weatherStation;
    }

    @Override
    public void display() {
        System.out.println("Temperature:" + weatherStation.getTemperature());
        System.out.println("Pressure : " + weatherStation.getPressure());
    }
}

public class WeatherMOnitoring {

    public static void main(String[] args) {
        WeatherStation weatherStation = new ConcreteWeatherStation();
        MobileDisplay mobileDisplay= new MobileDisplay(weatherStation);

        weatherStation.addObserver(mobileDisplay);
        weatherStation.setHumidity(99.0);
        weatherStation.setPressure(99322.0);
        weatherStation.setTemperature(230);

//        ((ConcreteWeatherStation)weatherStation).shutdown();
    }
}
