package org.example.designProblems.practice.Uber.model;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class RandomDriverStrategy implements MatchingStrategy {
    private final Random random = new Random();

    @Override
    public Optional<Driver> match(Map<Integer, Driver> drivers) {
        if (drivers.isEmpty()) return null;
        int index = random.nextInt(drivers.size());
        return Optional.ofNullable(drivers.values().stream().skip(index).findFirst().orElse(null));
    }
}
