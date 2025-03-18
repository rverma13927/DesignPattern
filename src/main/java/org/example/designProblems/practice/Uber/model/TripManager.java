package org.example.designProblems.practice.Uber.model;

import org.example.DesignPattern.Strategy.with.Strategy.DriveStrategy;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TripManager {

    private Map<Integer,Trip>  tripMap = new ConcurrentHashMap<>();
    private static TripManager tripManager;

    public static TripManager getInstance() {
        if (tripManager == null) {
            synchronized (TripManager.class) {
                if (tripManager == null) {
                    tripManager = new TripManager();
                }
            }
        }
        return tripManager;
    }

    public Optional<Trip> creatTrip(Trip trip,PricingStrategy pricingStrategy,MatchingStrategy matchingStrategy){
        Optional<Driver> match = matchingStrategy.match(trip);
        double v = pricingStrategy.calculatePrice(trip);
        if(match.isPresent()) {
            trip.setDriver(match.get());
            trip.setPrice(v);
            trip.setPaymentStatus(PaymentStatus.COMPLETED);
            tripMap.put(trip.getId(), trip);
        }else return Optional.empty();
        return Optional.of(trip);
    }
}
