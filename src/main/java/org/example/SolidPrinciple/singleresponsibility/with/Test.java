package org.example.SolidPrinciple.singleresponsibility.with;

public class Test {
}

class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}

// Handles salary calculation
class SalaryCalculator {
    public double calculateSalary(Employee employee) {
        // Salary calculation logic
        return employee.getSalary();
    }
}

// Handles database operations
class EmployeeRepository {
    public void saveToDatabase(Employee employee) {
        // Database logic
        System.out.println("Employee saved to database");
    }
}

// Handles report generation
class EmployeeReport {
    public void generateReport(Employee employee) {
        // Report generation logic
        System.out.println("Employee report generated for: " + employee.getName());
    }
}
