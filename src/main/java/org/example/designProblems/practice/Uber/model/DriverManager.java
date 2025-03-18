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

    public Map<Integer, Driver> getAllDriver(){
        return driverMap;
    }
    public Optional<Driver> getDriver(MatchingStrategy matchingStrategy){
        if (driverMap.isEmpty()) {
            throw new IllegalStateException("No drivers available");
        }
        Optional<Driver> match = matchingStrategy.match(driverMap);
        return match;
    }
}
