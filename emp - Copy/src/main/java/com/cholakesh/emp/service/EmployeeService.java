package com.cholakesh.emp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cholakesh.emp.entity.Employee;
import com.cholakesh.emp.repository.EmployeeRepository;
import java.util.Date;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

  
    public Employee addEmployee(Employee employee) {
        // Implement data validation logic here
        validateEmployeeData(employee);
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    private void validateEmployeeData(Employee employee) {
        // Custom validation methods for each field
        validateEmployeeId(employee.getEmployeeId());
        validateName(employee.getFirstName(), "FirstName");
        validateName(employee.getLastName(), "LastName");
        validateEmail(employee.getEmail());
        validatePhoneNumbers(employee.getPhoneNumbers());
        validateDOJ(employee.getDateOfJoining());
        validateSalary(employee.getSalaryPerMonth());
    }

    private void validateEmployeeId(long l) {
        // Add your validation logic for Employee ID here
        // For example, you can check if it's a positive integer.
        if (l <= 0) {
            throw new IllegalArgumentException("Employee ID must be a positive integer.");
        }
    }

    private void validateName(String name, String fieldName) {
        // Add your validation logic for FirstName and LastName here
        // For example, you can check if the name is not empty and has a valid format.
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }
    }

    private void validateEmail(String email) {
        // Add your email validation logic here
        // You can use a regular expression or a library like Apache Commons Validator.
        // For simplicity, we'll check for a basic format.
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid Email format.");
        }
    }

    private void validatePhoneNumbers(List<String> phoneNumbers) {
        // Add your validation logic for phone numbers here
        // For example, you can check if the list is not empty and each number has a valid format.
        if (phoneNumbers == null || phoneNumbers.isEmpty()) {
            throw new IllegalArgumentException("Phone Numbers are required.");
        }
        for (String phoneNumber : phoneNumbers) {
            if (!phoneNumber.matches("^[0-9]{10}$")) {
                throw new IllegalArgumentException("Invalid Phone Number format.");
            }
        }
    }

    private void validateDOJ(Date dateOfJoining) {
        // Add your validation logic for Date of Joining here
        // For example, you can check if it's not in the future.
        if (dateOfJoining == null || dateOfJoining.after(new Date())) {
            throw new IllegalArgumentException("Invalid Date of Joining.");
        }
    }

    private void validateSalary(double salary) {
        // Add your validation logic for Salary here
        // For example, you can check if it's greater than zero.
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary must be greater than zero.");
        }
    }
    
    
}
