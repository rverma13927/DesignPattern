package org.example.DesignPattern.Decorator.decorator;

import org.example.DesignPattern.Decorator.Car;

public class SportDecorator extends CarDecorator{
    public SportDecorator(Car car) {
        super(car);
    }
    @Override
    public void assemble(){
        super.assemble();
        System.out.print(" Adding features of Sports Car.");
    }
}
