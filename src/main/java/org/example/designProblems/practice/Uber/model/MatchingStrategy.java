package org.example.designProblems.practice.Uber.model;

import java.util.Map;
import java.util.Optional;

public interface MatchingStrategy {
    Optional<Driver> match(Map<Integer, Driver> trip);
}
