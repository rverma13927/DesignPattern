package org.example.practice.Uber;

import org.example.designProblems.practice.Uber.model.*;
import org.junit.jupiter.api.*;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DriverManagerTest {

    DriverManager driverManager;
    @BeforeEach
    void setup(){
            driverManager = new DriverManager();
    }

    @Test
    @Order(1)
    void testwhenDriverNotavialble(){
        assertThrows(IllegalStateException.class, () -> driverManager.getDriver(new RandomDriverStrategy()));
    }

    @Test
    @Order(10)
    void addDriver(){
        Driver driver = new Driver(1,"Test",1);
        assertTrue(driverManager.addDriver(driver));
        assertTrue(1==driverManager.getAllDriver().size());
    }

    @Test
    @Order(102)
    void getDriver(){
        assertNotNull(driverManager.getDriver(new RandomDriverStrategy()));
    }

    @Test
    void testConcurrency() throws InterruptedException {
        Driver driver1 = new Driver(1, "DriverOne",2);
        Driver driver2 = new Driver(2, "DriverTwo",4);
        driverManager.addDriver(driver1);
        driverManager.addDriver(driver2);

        Thread t1 = new Thread(() -> driverManager.getDriver(new RatingMatchingStrategy()));
        Thread t2 = new Thread(() -> driverManager.getDriver(new RandomDriverStrategy()));

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(driverManager.getAllDriver().size());
        assertEquals(2, driverManager.getAllDriver().size());
    }
    @Test
    void testConcurrentDriverAdditionAndSelection() throws InterruptedException {
        DriverManager driverManager = DriverManager.getDriverManagerInstance();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Adding multiple drivers concurrently
        for (int i = 0; i < 10; i++) {
            int driverId = i + 1;
            executorService.execute(() -> driverManager.addDriver(new Driver(driverId, "Driver" + driverId, Math.random() * 5)));
        }

        executorService.shutdown();
        executorService.awaitTermination(2, TimeUnit.SECONDS);

        // Fetching drivers concurrently
        ExecutorService fetchService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            fetchService.execute(() -> {
                MatchingStrategy strategy = new RandomDriverStrategy();
                Optional<Driver> driver = driverManager.getDriver(strategy);
                assertNotNull(driver);
            });
        }

        fetchService.shutdown();
        fetchService.awaitTermination(2, TimeUnit.SECONDS);

        assertEquals(10, driverManager.getAllDriver().size());
    }



}
