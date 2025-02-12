package org.example.SolidPrinciple.interfacesegregation;



interface OrderPlacer {
    void placeOrder();
}

interface OrderTracker {
    void trackOrder();
}

interface OrderHandler {
    void acceptOrder();
    void prepareOrder();
}

interface DeliveryHandler {
    void deliverOrder();
}

class Customer1 implements OrderPlacer, OrderTracker {
    @Override
    public void placeOrder() {
        System.out.println("Order placed");
    }

    @Override
    public void trackOrder() {
        System.out.println("Tracking order");
    }
}

class Restaurant implements OrderHandler {
    @Override
    public void acceptOrder() {
        System.out.println("Order accepted");
    }

    @Override
    public void prepareOrder() {
        System.out.println("Order prepared");
    }
}

class DeliveryPerson implements DeliveryHandler {
    @Override
    public void deliverOrder() {
        System.out.println("Order delivered");
    }
}

public class with {
}
