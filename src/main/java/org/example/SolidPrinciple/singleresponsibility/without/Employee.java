package org.example.SolidPrinciple.singleresponsibility.without;

/**
 *
 * The Employee class has multiple responsibilities:
 * Managing employee data.
 * Calculating salary.
 * Saving employee data to the database.
 * Generating reports.
 *
 */
public class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Calculate employee salary
    public double calculateSalary() {
        // Salary calculation logic
        return salary;
    }

    // Save employee to the database
    public void saveToDatabase() {
        // Database saving logic
        System.out.println("Employee saved to database");
    }

    // Generate employee report
    public void generateReport() {
        // Report generation logic
        System.out.println("Employee report generated");
    }
}
