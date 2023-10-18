package com.cholakesh.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cholakesh.emp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

