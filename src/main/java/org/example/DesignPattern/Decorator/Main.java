package org.example.DesignPattern.Decorator;

import org.example.DesignPattern.Decorator.decorator.CarDecorator;
import org.example.DesignPattern.Decorator.decorator.LuxuryCar;
import org.example.DesignPattern.Decorator.decorator.SportDecorator;


/*
*  https://www.digitalocean.com/community/tutorials/decorator-design-pattern-in-java-example
*
*
*
*/
public class Main {
    public static void main(String[] args) {
//        CarDecorator carDecorator = new SportDecorator(new LuxuryCar(new BasicCar()));
        CarDecorator carDecorator = new SportDecorator(new BasicCar());
        carDecorator.assemble();
    }
}
