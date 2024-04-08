package org.example.DesignPattern.Decorator;

public class SportCar implements Car{
    @Override
    public void assemble() {
        System.out.println("Sport Car feature");
    }
}
