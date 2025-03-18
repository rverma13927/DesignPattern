package org.example.designProblems.practice.Uber.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Trip {
    private Integer id;
    private Rider rider;
    private Driver driver;
    private String from;
    private String to;
    private PaymentStatus paymentStatus;
    private double price;
    private double distance;

    public Trip(Integer id, Rider rider, String from, String to, double distance) {
        this.id = id;
        this.rider = rider;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }
}
