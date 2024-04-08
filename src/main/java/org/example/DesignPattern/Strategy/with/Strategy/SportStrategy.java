package org.example.DesignPattern.Strategy.with.Strategy;

public class SportStrategy implements DriveStrategy{
    @Override
    public void drive() {
        System.out.println("Sport drive capability using strategy!");
    }
}
