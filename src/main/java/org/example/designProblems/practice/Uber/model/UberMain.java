package org.example.designProblems.practice.Uber.model;

import java.util.Optional;

class UberMain {
    public static void main(String[] args) {
        Rider rider =new Rider(1,"Test");
        Driver driver =new Driver(1,"Test2",1);

        RiderManager riderManager = new RiderManager();
        riderManager.addRider(rider);

        DriverManager driverManager = new DriverManager();
        driverManager.addDriver(driver);

        TripManager tripManager = TripManager.getInstance();
        Trip trip = new Trip(1,rider,"hyd","to",21);
        Optional<Trip> trip1 = tripManager.creatTrip(trip, new RatingPricingStrategyImpl(), new RatingMatchingStrategy());

        if(trip1.isPresent()){
            System.out.println(trip1.get().getPrice());
            System.out.println(trip1.get().getPaymentStatus());
        }else{
            System.out.println("No driver available");
        }



    }
}
