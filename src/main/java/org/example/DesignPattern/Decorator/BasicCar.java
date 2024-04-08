package org.example.DesignPattern.Decorator;

public class BasicCar implements Car{
    @Override
    public void assemble() {
        System.out.println("Basic car features!");
    }
}
