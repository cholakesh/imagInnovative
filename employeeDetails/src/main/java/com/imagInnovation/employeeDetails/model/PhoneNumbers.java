package com.imagInnovation.employeeDetails.model;

import org.springframework.stereotype.Component;

@Component
public class PhoneNumbers {
	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
