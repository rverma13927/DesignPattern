package org.example.designProblems.problemWithUML.ParkingLot;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
public class FInal {
}
// ===== Core Enums =====
enum VehicleType {
    CAR, BIKE, TRUCK
}

// ===== Vehicle Hierarchy =====
abstract class Vehicle {
    private final String licensePlate;
    private final VehicleType type;

    public Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public VehicleType getType() { return type; }
    public String getLicensePlate() { return licensePlate; }
}

class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, VehicleType.CAR);
    }
}

class Bike extends Vehicle {
    public Bike(String licensePlate) {
        super(licensePlate, VehicleType.BIKE);
    }
}

class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate, VehicleType.TRUCK);
    }
}

// ===== Parking Spot =====
class ParkingSpot {
    private final int spotId;
    private final VehicleType type;
    private boolean isAvailable;
    private Vehicle parkedVehicle;

    public ParkingSpot(int spotId, VehicleType type) {
        this.spotId = spotId;
        this.type = type;
        this.isAvailable = true;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        return isAvailable && vehicle.getType() == type;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isAvailable = false;
    }

    public void freeSpot() {
        this.parkedVehicle = null;
        this.isAvailable = true;
    }

    public int getSpotId() { return spotId; }
    public VehicleType getType() { return type; }
    public boolean isAvailable() { return isAvailable; }
}

// ===== Parking Floor =====
class ParkingFloor {
    private final int floorNumber;
    private final List<ParkingSpot> spots;
    private final Map<VehicleType, Queue<ParkingSpot>> availableSpots;

    public ParkingFloor(int floorNumber, List<ParkingSpot> spots) {
        this.floorNumber = floorNumber;
        this.spots = spots;
        this.availableSpots = new EnumMap<>(VehicleType.class);

        // Initialize available spots by type
        for (VehicleType type : VehicleType.values()) {
            availableSpots.put(type, new LinkedList<>());
        }

        for (ParkingSpot spot : spots) {
            availableSpots.get(spot.getType()).add(spot);
        }
    }

    public ParkingSpot findAvailableSpot(VehicleType type) {
        Queue<ParkingSpot> spotsQueue = availableSpots.get(type);
        return spotsQueue.isEmpty() ? null : spotsQueue.peek();
    }

    public void markSpotOccupied(ParkingSpot spot) {
        availableSpots.get(spot.getType()).remove();
    }

    public void markSpotAvailable(ParkingSpot spot) {
        availableSpots.get(spot.getType()).add(spot);
    }
}

// ===== Parking Lot (Singleton) =====
class ParkingLot {
    private static ParkingLot instance;
    private final List<ParkingFloor> floors;

    private ParkingLot() {
        this.floors = new ArrayList<>();
        initializeParkingLot();
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            synchronized (ParkingLot.class) {
                if (instance == null) {
                    instance = new ParkingLot();
                }
            }
        }
        return instance;
    }

    private void initializeParkingLot() {
        // Create 3 floors with 10 spots each (for demo)
        for (int i = 1; i <= 3; i++) {
            List<ParkingSpot> spots = new ArrayList<>();
            // 5 car spots
            for (int j = 1; j <= 5; j++) {
                spots.add(new ParkingSpot(j, VehicleType.CAR));
            }
            // 3 bike spots
            for (int j = 6; j <= 8; j++) {
                spots.add(new ParkingSpot(j, VehicleType.BIKE));
            }
            // 2 truck spots
            for (int j = 9; j <= 10; j++) {
                spots.add(new ParkingSpot(j, VehicleType.TRUCK));
            }
            floors.add(new ParkingFloor(i, spots));
        }
    }

    public ParkingSpot assignSpot(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.findAvailableSpot(vehicle.getType());
            if (spot != null) {
                spot.parkVehicle(vehicle);
                floor.markSpotOccupied(spot);
                return spot;
            }
        }
        return null; // No available spot
    }

    public void freeSpot(ParkingSpot spot) {
        spot.freeSpot();
        for (ParkingFloor floor : floors) {
            if (floor.findAvailableSpot(spot.getType()) == spot) {
                floor.markSpotAvailable(spot);
                break;
            }
        }
    }
}

// ===== Parking Ticket =====
class ParkingTicket {
    private final String ticketId;
    private final ParkingSpot spot;
    private final LocalDateTime entryTime;
    private final Vehicle vehicle;

    public ParkingTicket(ParkingSpot spot, Vehicle vehicle) {
        this.ticketId = UUID.randomUUID().toString();
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
        this.vehicle = vehicle;
    }

    public ParkingSpot getSpot() { return spot; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public Vehicle getVehicle() { return vehicle; }
}

// ===== Entry/Exit Gates =====
class EntryGate {
    private final ParkingLot parkingLot;

    public EntryGate() {
        this.parkingLot = ParkingLot.getInstance();
    }

    public ParkingTicket generateTicket(Vehicle vehicle) {
        ParkingSpot spot = parkingLot.assignSpot(vehicle);
        if (spot == null) {
            throw new IllegalStateException("Parking lot is full");
        }
        return new ParkingTicket(spot, vehicle);
    }
}

class ExitGate {
    private final ParkingLot parkingLot;

    public ExitGate() {
        this.parkingLot = ParkingLot.getInstance();
    }

    public double processPayment(ParkingTicket ticket) {
        double fee = calculateFee(ticket);
        parkingLot.freeSpot(ticket.getSpot());
        return fee;
    }

    private double calculateFee(ParkingTicket ticket) {
        long hours = Duration.between(ticket.getEntryTime(), LocalDateTime.now()).toHours();
        hours = Math.max(1, hours); // Minimum 1 hour charge

        return switch(ticket.getVehicle().getType()) {
            case CAR -> hours * 2.0;
            case BIKE -> hours * 1.0;
            case TRUCK -> hours * 5.0;
        };
    }
}

// ===== Main Application =====
 class ParkingLotSystem {
    public static void main(String[] args) {
        // Initialize gates
        EntryGate entryGate = new EntryGate();
        ExitGate exitGate = new ExitGate();

        // Vehicles entering
        Vehicle car1 = new Car("CAR-001");
        Vehicle bike1 = new Bike("BIKE-001");
        Vehicle truck1 = new Truck("TRUCK-001");

        // Generate tickets
        ParkingTicket carTicket = entryGate.generateTicket(car1);
        ParkingTicket bikeTicket = entryGate.generateTicket(bike1);
        ParkingTicket truckTicket = entryGate.generateTicket(truck1);

        // Simulate time passing (2 hours)
        try {
            Thread.sleep(100); // Simulate 2 hours parking
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Process exits
        System.out.println("Car fee: $" + exitGate.processPayment(carTicket));
        System.out.println("Bike fee: $" + exitGate.processPayment(bikeTicket));
        System.out.println("Truck fee: $" + exitGate.processPayment(truckTicket));
    }
}