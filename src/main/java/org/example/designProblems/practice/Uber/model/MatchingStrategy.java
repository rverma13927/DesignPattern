package org.example.designProblems.practice.Uber.model;

import java.util.Optional;

public interface MatchingStrategy {
    Optional<Driver> match(Trip trip);
}
