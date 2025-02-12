package org.example.SolidPrinciple.interfacesegregation;

interface FoodDeliveryService {
    void placeOrder();
    void acceptOrder();
    void prepareOrder();
    void deliverOrder();
    void trackOrder();
}

class Customer implements FoodDeliveryService {
    @Override
    public void placeOrder() {
        System.out.println("Order placed");
    }

    @Override
    public void acceptOrder() {
        throw new UnsupportedOperationException("Customer cannot accept orders");
    }

    @Override
    public void prepareOrder() {
        throw new UnsupportedOperationException("Customer cannot prepare orders");
    }

    @Override
    public void deliverOrder() {
        throw new UnsupportedOperationException("Customer cannot deliver orders");
    }

    @Override
    public void trackOrder() {
        System.out.println("Tracking order");
    }
}

public class Without {
}