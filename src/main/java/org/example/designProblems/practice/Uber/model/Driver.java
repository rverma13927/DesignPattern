package org.example.designProblems.practice.Uber.model;

import lombok.Setter;

@Setter
public class Driver extends Person {
    private Double rating;

    public Driver(Integer id,String name,double rating) {
        super(id,name);
        this.rating = rating;
    }

    public double getRating() {
      return rating;
    }
}
