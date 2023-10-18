package com.imagInnovation.employeeDetails.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagInnovation.employeeDetails.Repository.EmployeeRepo;
import com.imagInnovation.employeeDetails.entity.Employee;
import com.imagInnovation.employeeDetails.model.EmployeeRequest;
import com.imagInnovation.employeeDetails.model.TaxDeduction;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	public void saveEmpoloyee(EmployeeRequest employee) {
		validateEmployeeData(employee);
		Employee emp= new Employee(employee.getEmployeeId(),employee.getFirstName(),employee.getLastName(),employee.getEmail(),employee.getPhoneNumbers(),employee.getDateOfJoining(),employee.getMonSalary());
		employeeRepo.save(emp);
	}
	
	private void validateEmployeeData(EmployeeRequest employee) {
        // Custom validation methods for each field
        validateName(employee.getFirstName(), "FirstName");
        validateName(employee.getLastName(), "LastName");
        validateEmail(employee.getEmail());
        validatePhoneNumbers(employee.getPhoneNumbers());
        validateDOJ(employee.getDateOfJoining());
    }
	

    private void validateName(String name, String fieldName) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }
    }

    private void validateEmail(String email) {
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid Email format.");
        }
    }

    private void validatePhoneNumbers(List<String> phoneNumbers) {
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
        if (dateOfJoining == null || dateOfJoining.after(new Date())) {
            throw new IllegalArgumentException("Invalid Date of Joining.");
        }
    }

    public Employee getEmployeeById(Integer employeeId) {
        return employeeRepo.findById(employeeId).orElse(null);
    }


}