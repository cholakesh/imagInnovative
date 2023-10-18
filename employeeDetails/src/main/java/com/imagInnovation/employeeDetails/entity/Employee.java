package com.imagInnovation.employeeDetails.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;
	@Column(name="FIRST_NAME",nullable = false)
	private String firstName;
	@Column(name="LAST_NAME",nullable = false)
	private String lastName;
	@Column(name="EMAIL",nullable = false)
	private String email;
	@Column(name="PHONE_NUMBERS")
	@ElementCollection
	private List<String> phoneNumbers;
	@Column(name="DATE_OF_JOINING")
	@Temporal(TemporalType.DATE)
	private Date dateOfJoining;
	@Column(name="MONTH_SALARY",nullable = false)
	private double monSalary;
	
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
	public Employee(Integer employeeId, String firstName, String lastName, String email,
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
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNumbers=" + phoneNumbers + ", dateOfJoining=" + dateOfJoining + ", monSalary="
				+ monSalary + "]";
	}
	
	

}
