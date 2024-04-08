package org.example.DesignPattern.Decorator.decorator;

import org.example.DesignPattern.Decorator.Car;

public class LuxuryCar extends CarDecorator {

    public LuxuryCar(Car car) {
        super(car);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.println("Adding luxury car features!");
    }
}
