package com.cholakesh.emp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.cholakesh.emp.entity.Employee;
import com.cholakesh.emp.entity.TaxInfo;
import com.cholakesh.emp.service.EmployeeService;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Create endpoint for storing employee details
    @PostMapping("/store")
    public Employee storeEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    // Create endpoint for getting all employees
    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Create endpoint for getting an employee by ID
    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }
    
    
    
    // Create endpoint for calculating and returning tax information
    @GetMapping("/tax/{employeeId}")
    public TaxInfo calculateTax(@PathVariable Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }

        double yearlySalary = calculateYearlySalary(employee);
        double taxAmount = calculateTaxAmount(yearlySalary);
        double cessAmount = calculateCessAmount(yearlySalary);

        return new TaxInfo(employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(),
                yearlySalary, taxAmount, cessAmount);
    }

    // Method to calculate the yearly salary based on date of joining
    private double calculateYearlySalary(Employee employee) {
        Calendar currentYearStart = Calendar.getInstance();
        currentYearStart.set(Calendar.YEAR, currentYearStart.get(Calendar.YEAR) - 1);
        currentYearStart.set(Calendar.MONTH, Calendar.APRIL);
        currentYearStart.set(Calendar.DAY_OF_MONTH, 1);

        Calendar currentYearEnd = Calendar.getInstance();
        currentYearEnd.set(Calendar.MONTH, Calendar.MARCH);
        currentYearEnd.set(Calendar.DAY_OF_MONTH, 31);

        Calendar joinDate = Calendar.getInstance();
        joinDate.setTime(employee.getDateOfJoining());

        if (joinDate.after(currentYearEnd)) {
            return 0;
        }

        if (joinDate.before(currentYearStart)) {
            joinDate = currentYearStart;
        }

        int monthsWorked = 0;

        while (joinDate.before(currentYearEnd)) {
            monthsWorked++;
            joinDate.add(Calendar.MONTH, 1);
        }

        return employee.getSalaryPerMonth() * monthsWorked;
    }


    // Method to calculate tax amount
    private double calculateTaxAmount(double yearlySalary) {
        double taxAmount = 0;

        if (yearlySalary <= 250000) {
            // No tax for income up to 250,000
            taxAmount = 0;
        } else if (yearlySalary <= 500000) {
            // 5% tax for income between 250,001 and 500,000
            taxAmount = (yearlySalary - 250000) * 0.05;
        } else if (yearlySalary <= 1000000) {
            // 10% tax for income between 500,001 and 1,000,000
            taxAmount = 250000 * 0.05 + (yearlySalary - 500000) * 0.1;
        } else {
            // 20% tax for income over 1,000,000
            taxAmount = 250000 * 0.05 + 500000 * 0.1 + (yearlySalary - 1000000) * 0.2;
        }

        return taxAmount;
    }

    private double calculateCessAmount(double yearlySalary) {
        if (yearlySalary > 2500000) {
            return (yearlySalary - 2500000) * 0.02;
        } else {
            return 0;
        }
    }

}





