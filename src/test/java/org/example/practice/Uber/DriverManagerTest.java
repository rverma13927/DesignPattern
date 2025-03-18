package org.example.practice.Uber;

import junit.framework.Assert;
import org.example.designProblems.practice.Uber.model.Driver;
import org.example.designProblems.practice.Uber.model.DriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
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
        assertThrows(IllegalStateException.class, () -> driverManager.getDriver());
    }

    @Test
    @Order(10)
    void addDriver(){
        Driver driver = new Driver(1,"Test");
        assertTrue(driverManager.addDriver(driver));
    }

    @Test
    @Order(102)
    void getDriver(){
        assertNotNull(driverManager.getDriver());
    }




}
