package org.example.DesignPattern.Strategy.with.Strategy;

import org.example.DesignPattern.Strategy.with.Vehicle;

public class PassengerVehicle extends Vehicle {

    public PassengerVehicle() {
        super(new NormalStrategy());
    }
}
