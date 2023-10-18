package com.imagInnovation.employeeDetails.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.imagInnovation.employeeDetails.entity.Employee;
import com.imagInnovation.employeeDetails.model.EmployeeRequest;
import com.imagInnovation.employeeDetails.model.TaxDeduction;
import com.imagInnovation.employeeDetails.service.EmployeeService;

import jakarta.validation.Valid;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/storeDetails")
	public ResponseEntity<String> storeEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
		employeeService.saveEmpoloyee(employeeRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	
	 @GetMapping("/taxDeduction/{employeeId}")
	    public TaxDeduction calculateTax(@PathVariable Integer employeeId) {
	        Employee employee = employeeService.getEmployeeById(employeeId);
	        if (employee == null) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
	        }

	        double yearlySalary = calculateYearlySalary(employee);
	        double taxAmount = calculateTaxAmount(yearlySalary);
	        double cessAmount = calculateCessAmount(yearlySalary);

	        return new TaxDeduction(employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(),
	                yearlySalary, taxAmount, cessAmount);
	    }

	    // Method to calculate the yearly salary based on date of joining
	    private double calculateYearlySalary(Employee employee) {
	        // Calculate total months worked in the current financial year (April to March)
	        Calendar currentYearStart = Calendar.getInstance();
	        currentYearStart.set(Calendar.MONTH, Calendar.APRIL);
	        currentYearStart.set(Calendar.DAY_OF_MONTH, 1);

	        Calendar currentYearEnd = Calendar.getInstance();
	        currentYearEnd.set(Calendar.MONTH, Calendar.MARCH);
	        currentYearEnd.set(Calendar.DAY_OF_MONTH, 31);

	        Calendar joinDate = Calendar.getInstance();
	        joinDate.setTime(employee.getDateOfJoining());

	        if (joinDate.before(currentYearStart)) {
	            joinDate = currentYearStart;
	        }
	        if (joinDate.after(currentYearEnd)) {
	            joinDate = currentYearEnd;
	        }

	        double monthsWorked = (currentYearEnd.get(Calendar.YEAR) - joinDate.get(Calendar.YEAR)) * 12
	                + currentYearEnd.get(Calendar.MONTH) - joinDate.get(Calendar.MONTH) + 1;

	        return employee.getMonSalary() * monthsWorked;
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

	    // Method to calculate cess amount
	    private double calculateCessAmount(double yearlySalary) {
	        if (yearlySalary > 2500000) {
	            // 2% cess on the amount above 2,500,000
	            return (yearlySalary - 2500000) * 0.02;
	        } else {
	            return 0;
	        }
	    }
}
