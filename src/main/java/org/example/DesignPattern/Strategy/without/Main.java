package org.example.DesignPattern.Strategy.without;



/*
Problem:
Multiple subclass have same capability but we have to duplicate in this scenarios
for example sport and off road vehicle have sport vehicle capability.
*/

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new SportVehicle();
        vehicle.drive();
    }
}
