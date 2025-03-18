package org.example.designProblems.practice.Uber.model;

public class DefaultPricingStrategyImpl implements PricingStrategy {


    @Override
    public double calculatePrice(Trip trip) {
         return  10;
    }
}
