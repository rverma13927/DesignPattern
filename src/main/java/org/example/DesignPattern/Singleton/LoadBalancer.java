package org.example.DesignPattern.Singleton;

import java.util.ArrayList;
import java.util.List;

public class LoadBalancer {
    private static LoadBalancer instance;
    private List<String> servers;
    private int currentIndex;

    private LoadBalancer() {
        servers = new ArrayList<>();
        currentIndex = 0;
    }

    public static LoadBalancer getInstance() {
        if (instance == null) {
            synchronized (LoadBalancer.class) {
                if (instance == null) {
                    instance = new LoadBalancer();
                }
            }
        }
        return instance;
    }

    public void addServer(String server) {
        servers.add(server);
    }

    public String getNextServer() {
        if (servers.isEmpty()) {
            throw new IllegalStateException("No servers available");
        }
        String server = servers.get(currentIndex);
        currentIndex = (currentIndex + 1) % servers.size(); // Round-robin
        return server;
    }

    public static void main(String[] args) {
        LoadBalancer loadBalancer = LoadBalancer.getInstance();

        // Add servers
        loadBalancer.addServer("Server1");
        loadBalancer.addServer("Server2");
        loadBalancer.addServer("Server3");

        // Distribute requests
        for (int i = 0; i < 10; i++) {
            System.out.println("Request " + i + " routed to: " + loadBalancer.getNextServer());
        }
    }
}