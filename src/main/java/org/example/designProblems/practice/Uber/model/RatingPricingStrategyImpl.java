package org.example.designProblems.practice.Uber.model;

public class RatingPricingStrategyImpl implements PricingStrategy {
    @Override
    public double calculatePrice(Trip trip) {
        return 20;
    }
}
