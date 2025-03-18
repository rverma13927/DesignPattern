package org.example.designProblems.practice.Uber.model;

import java.util.Optional;

public class RatingMatchingStrategy implements MatchingStrategy{
    @Override
    public Optional<Driver> match(Trip trip) {
        DriverManager driverManagerInstance = DriverManager.getDriverManagerInstance();

        return driverManagerInstance.getDriver();

    }
}
