package org.example.DesignPattern.Factory;

public class ShapeFactory {
    //Factory method
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Sqaure();
        }
        return null;
    }
}
