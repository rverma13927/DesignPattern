package org.example.designProblems.practice.carRentalBOoking;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Rental {
}


// Abstract Factory Pattern for Vehicle Creation
abstract class Vehicle {
    protected String id;
    protected String model;
    protected VehicleStatus status;

    public abstract double getRentalPrice();
}

// Car Classes
class Car extends Vehicle {
    @Override
    public double getRentalPrice() {
        return 0;
    }
}
class Sedan extends Car {
    public Sedan(String id, String model) {
        this.id = id;
        this.model = model;
        this.status = VehicleStatus.AVAILABLE;
    }
    public double getRentalPrice() { return 50.0; }
}

class SUV extends Car {
    public SUV(String id, String model) {
        this.id = id;
        this.model = model;
        this.status = VehicleStatus.AVAILABLE;
    }
    public double getRentalPrice() { return 80.0; }
}

// Bike Classes
class Bike extends Vehicle {
    @Override
    public double getRentalPrice() {
        return 0;
    }
}
class SportsBike extends Bike {
    public SportsBike(String id, String model) {
        this.id = id;
        this.model = model;
        this.status = VehicleStatus.AVAILABLE;
    }
    public double getRentalPrice() { return 30.0; }
}

class CruiserBike extends Bike {
    public CruiserBike(String id, String model) {
        this.id = id;
        this.model = model;
        this.status = VehicleStatus.AVAILABLE;
    }
    public double getRentalPrice() { return 40.0; }
}

// Bicycle Classes
class Bicycle extends Vehicle {
    @Override
    public double getRentalPrice() {
        return 0;
    }
}
class MountainBike extends Bicycle {
    public MountainBike(String id, String model) {
        this.id = id;
        this.model = model;
        this.status = VehicleStatus.AVAILABLE;
    }
    public double getRentalPrice() { return 15.0; }
}

class RoadBike extends Bicycle {
    public RoadBike(String id, String model) {
        this.id = id;
        this.model = model;
        this.status = VehicleStatus.AVAILABLE;
    }
    public double getRentalPrice() { return 20.0; }
}

class VehicleFactory {
    public static Vehicle createVehicle(String type, String id, String model) {
        return switch (type) {
            case "SEDAN" -> new Sedan(id, model);
            case "SUV" -> new SUV(id, model);
            case "SPORTS_BIKE" -> new SportsBike(id, model);
            case "CRUISER_BIKE" -> new CruiserBike(id, model);
            case "MOUNTAIN_BIKE" -> new MountainBike(id, model);
            case "ROAD_BIKE" -> new RoadBike(id, model);
            default -> throw new IllegalArgumentException("Invalid vehicle type");
        };
    }
}

// Store Class

@Getter
@Setter
class Store {
    private String storeId;
    private String location;
    private final Map<String, Vehicle> vehicles = new HashMap<>();

    public Store(String storeId, String location) {
        this.storeId = storeId;
        this.location = location;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.id, vehicle);
    }

    public Vehicle getVehicle(String vehicleId) {
        return vehicles.get(vehicleId);
    }

    public Collection<Vehicle> getAvailableVehicles() {
        return vehicles.values().stream().filter(v -> v.status == VehicleStatus.AVAILABLE).toList();
    }

}

// Singleton Pattern for Inventory Management
class VehicleInventory {
    private static VehicleInventory instance;
    private final Map<String, Store> stores = new HashMap<>();

    private VehicleInventory() {}

    public static synchronized VehicleInventory getInstance() {
        if (instance == null) instance = new VehicleInventory();
        return instance;
    }

    public void addStore(Store store) {
        stores.put(store.getStoreId(), store);
    }

    public Store getStore(String storeId) {
        return stores.get(storeId);
    }

    public Collection<Store> getAllStores() {
        return stores.values();
    }
}

// Strategy Pattern for Payment
interface PaymentStrategy {
    void pay(double amount);
}
class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) { System.out.println("Paid " + amount + " using Credit Card."); }
}
class PayPalPayment implements PaymentStrategy {
    public void pay(double amount) { System.out.println("Paid " + amount + " using PayPal."); }
}

// Booking Class
class Booking {
    private String bookingId;
    private String userId;
    private String vehicleId;
    private double totalAmount;
    private BookingStatus status;

    public Booking(String bookingId, String userId, String vehicleId, double totalAmount) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.totalAmount = totalAmount;
        this.status = BookingStatus.PENDING;
    }

    public void makePayment(PaymentStrategy paymentStrategy) {
        paymentStrategy.pay(totalAmount);
        this.status = BookingStatus.CONFIRMED;
    }
}

// Enums for Status
enum VehicleStatus { AVAILABLE, BOOKED, IN_MAINTENANCE }
enum BookingStatus { PENDING, CONFIRMED, CANCELLED }

// User Class
class User {
    private String userId;
    private String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
// Main Class for Testing
 class VehicleRentalApp {
    public static void main(String[] args) {
        VehicleInventory inventory = VehicleInventory.getInstance();

        Store store1 = new Store("S001", "New York");
        Store store2 = new Store("S002", "Los Angeles");
        inventory.addStore(store1);
        inventory.addStore(store2);

        Vehicle car1 = VehicleFactory.createVehicle("SEDAN", "1", "Toyota Corolla");
        Vehicle bike1 = VehicleFactory.createVehicle("SPORTS_BIKE", "2", "Yamaha R1");
        Vehicle bicycle1 = VehicleFactory.createVehicle("MOUNTAIN_BIKE", "3", "Giant XTC");
        store1.addVehicle(car1);
        store2.addVehicle(bike1);
        store2.addVehicle(bicycle1);

        System.out.println("Store 1 Available Vehicles: " + store1.getAvailableVehicles().size());
        System.out.println("Store 2 Available Vehicles: " + store2.getAvailableVehicles().size());

        Booking booking = new Booking("B001", "U001", "2", bike1.getRentalPrice());
        booking.makePayment(new CreditCardPayment());
        System.out.println("Booking Confirmed!");
    }
}
