package org.example.DesignPattern.Factory;

public class Main {
    /*
    * The main advantage of this pattern is that we might need object in different places so we create factory to maintain the creation of object
    * and client can use the factory.
    *
    *However, the Factory Pattern offers several key advantages that go beyond this simple scenario:

    Flexibility and Extensibility:

    Adding New Shapes: If you need to introduce a new shape (e.g., Triangle), you only need to create a new class implementing the Shape interface and update the getShape() method in the ShapeFactoryImpl class. The client code (the main method) remains unchanged.
    Changing Shape Creation Logic: Imagine you have complex logic for creating shapes, such as fetching data from a configuration file, applying caching mechanisms, or performing dependency injection. The Factory Pattern encapsulates this logic within the getShape() method, making it easier to modify or enhance without affecting the client code.
    Loose Coupling:

    The client code is decoupled from the concrete classes of the objects it uses. It only interacts with the Shape interface, making the code more modular and easier to maintain.
    If you need to change the implementation of a specific shape (e.g., use a different Circle class), you only need to modify the ShapeFactoryImpl class. The client code remains unaffected.
    Centralized Control:

    The Factory Pattern provides a single point of control for object creation. This can be beneficial for:
    Logging: You can log the creation of objects.
    Resource Management: You can manage the allocation and deallocation of resources associated with object creation.
    Configuration: You can easily configure how objects are created based on external settings.
    * Abstract factory pattern : https://www.tutorialspoint.com/design_pattern/abstract_factory_pattern.htm
    */
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        // Get an object of Circle and call its draw method
        Shape circle = shapeFactory.getShape("CIRCLE");
        circle.draw();

    }
}
