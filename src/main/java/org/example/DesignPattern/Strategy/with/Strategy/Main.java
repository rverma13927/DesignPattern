package org.example.DesignPattern.Strategy.with.Strategy;

import org.example.DesignPattern.Strategy.with.SportVehicle;
import org.example.DesignPattern.Strategy.with.Vehicle;

public class Main {
    public static void main(String[] args) {
        Vehicle v = new SportVehicle();
        v.drive();
    }
}
