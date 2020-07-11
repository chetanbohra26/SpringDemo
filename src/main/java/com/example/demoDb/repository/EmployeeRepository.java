package com.example.demoDb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoDb.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
}
