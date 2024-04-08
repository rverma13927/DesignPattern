package org.example.DesignPattern.Strategy.with;

import org.example.DesignPattern.Strategy.with.Strategy.DriveStrategy;
import org.example.DesignPattern.Strategy.with.Strategy.SportStrategy;
import org.example.DesignPattern.Strategy.with.Vehicle;

public class SportVehicle extends Vehicle {

    public SportVehicle() {
        super(new SportStrategy());
    }
}
