package org.example.DesignPattern.Factory;

public class Main {
/*
* The main advantage of this pattern is that we might need object in different places so we create factory to maintain the creation of object
* and client can use the factory.
*
*
* Abstract factory pattern : https://www.tutorialspoint.com/design_pattern/abstract_factory_pattern.htm
*/
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        // Get an object of Circle and call its draw method
        Shape circle = shapeFactory.getShape("CIRCLE");
        circle.draw();

    }
}
