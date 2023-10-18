package com.imagInnovation.employeeDetails.model;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class EmployeeRequest {
	
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private List<String> phoneNumbers;
	private Date dateOfJoining;
	private double monSalary;
	
	
	
	public EmployeeRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public EmployeeRequest(Integer employeeId, String firstName, String lastName, String email,
			List<String> phoneNumbers, Date dateOfJoining, double monSalary) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumbers = phoneNumbers;
		this.dateOfJoining = dateOfJoining;
		this.monSalary = monSalary;
	}



	
	public Integer getEmployeeId() {
		return employeeId;
	}



	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}



	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public double getMonSalary() {
		return monSalary;
	}
	public void setMonSalary(double monSalary) {
		this.monSalary = monSalary;
	}

}
