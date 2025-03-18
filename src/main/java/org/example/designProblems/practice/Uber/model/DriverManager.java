package org.example.designProblems.practice.Uber.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DriverManager {

    private static  Map<Integer, Driver> driverMap = new HashMap<>();

    private static DriverManager driverManager = null;

    public static DriverManager getDriverManagerInstance(){

        if(driverManager ==null){
            synchronized (DriverManager.class){
                if(driverManager ==null){
                    driverManager = new DriverManager();
                }
            }
        }
        return driverManager;
    }


    public boolean addDriver(Driver driver){
        driverMap.put(driver.id,driver);
        return true;
    }
    public Optional<Driver> getDriver(){
        if (driverMap.isEmpty()) {
            throw new IllegalStateException("No drivers available");
        }
        return Optional.of(driverMap.get(1));
    }
}
