//package org.example.designProblems.problemWithUML.ParkingLot;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.List;
//
//class ParkingLot {
//    public static void main(String[] args) {
//        Vehicle vehicle;
//    }
//}
//
//@AllArgsConstructor
//@Getter
//@Setter
//class Vehicle{
//    private int id;
//    private String licencePlate;
//    private VehicleType vehicleType;
//}
//
//
//class Car extends Vehicle{
//
//    public Car(int id, String licencePlate,VehicleType vehicleType) {
//        super(id, licencePlate,vehicleType);
//    }
//}
//
//class Bike extends Vehicle{
//
//    public Bike(int id, String licencePlate,VehicleType vehicleType) {
//        super(id, licencePlate,vehicleType);
//    }
//}
//
//interface EntryGate{
//
//    void generateTicket(Vehicle vehicle);
//}
//
//class EntryGateImpl implements EntryGate{
//    @Override
//    public void generateTicket(Vehicle vehicle) {
//        System.out.println();
//    }
//}
//
//interface ExitGate1{
//    void processPayment(Vehicle vehicle);
//}
//
//class ExitGateImpl implements ExitGate1{
//
//    @Override
//    public void processPayment(Vehicle vehicle) {
//        System.out.println("Process payment");
//    }
//}
//
//enum VehicleType1{
//    CAR,TRUCK,BIKE
//}
//@Getter
//@Setter
//class ParkingSpot1{
//    private VehicleType vehicleType;
//    private boolean isAvailable;
//    private Vehicle vehicle;
//
//
//}
//@Getter
//class ParkingFloor1{
//    List<ParkingSpot> parkingSpotList;
//}
//
//class ParkingLotMain{
//    private ParkingLotMain parkingLotMain;
//    private List<ParkingFloor1> parkingFloors;
//    private ExitGate exitGate;
//    private ParkingLotMain(){
//    }
//    ParkingLotMain getInstance(){
//        if(parkingLotMain==null){
//            synchronized (ParkingLotMain.class){
//                if(parkingLotMain==null){
//                    parkingLotMain = new ParkingLotMain();
//                }
//            }
//        }
//        return parkingLotMain;
//    }
//
//    void bookSlot(Vehicle vehicle){
//
//        for(int i=0;i<parkingFloors.size();i++){
//            for(int j=0;j<parkingFloors.get(i).getParkingSpotList().size();j++){
//                if(parkingFloors.get(i).getParkingSpotList().get(j).isAvailable() &&parkingFloors.get(i).getParkingSpotList().get(j).getVehicleType().equals(vehicle.getVehicleType())){
//                    parkingFloors.get(i).getParkingSpotList().get(j).setAvailable(true);
//                }
//            }
//        }
//
//    }
//    void processPayment(Vehicle vehicle){
//        exitGate.processPayment(vehicle);
//    }
//}
//
//
