package org.example.DesignPattern.Decorator.decorator;

import org.example.DesignPattern.Decorator.Car;

public class CarDecorator implements Car {
    protected Car car;

    public CarDecorator(Car car) {
        this.car = car;
    }

    @Override
    public void assemble() {
        this.car.assemble();
    }
}
