package org.example.designProblems.practice.Uber.model;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class RatingMatchingStrategy implements MatchingStrategy{
    @Override
    public Optional<Driver> match(Map<Integer, Driver> driverMap) {

       return Optional.ofNullable(driverMap.values().stream().max((d1, d2) -> Double.compare(d1.getRating(), d2.getRating())).orElse(null));

    }
}

