package com.imagInnovation.employeeDetails.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imagInnovation.employeeDetails.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

	@Query(value="SELECT * FROM Employee e WHERE DATE_OF_JOINING between ?1 and ?2",nativeQuery = true)
    List<Employee> findEmployeesByDateRange(Date startDate,Date endDate);
}
