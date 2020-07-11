package com.example.demoDb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoDb.exception.ResourceNotFoundException;
import com.example.demoDb.model.Employee;
import com.example.demoDb.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	//Home
	@RequestMapping("/")
	public String welcome() {
		return "Ram Ram..!";
	}
	
	//get employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return this.empRepo.findAll();
	}
	
	//get employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
		Employee emp = this.empRepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Record not found for id : "+employeeId));
		return ResponseEntity.ok().body(emp);
	}
	
	//save employee
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		System.out.println("Save method called..!");
		return this.empRepo.save(employee);
	}
	
	//update employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId, 
			 @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException{
		Employee emp = this.empRepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Record not found for id : "+employeeId));
		emp.setEmail(employeeDetails.getEmail());
		emp.setFirstName(employeeDetails.getFirstName());
		emp.setLastName(employeeDetails.getLastName());
		
		return ResponseEntity.ok(this.empRepo.save(emp));
	}
	
	//delete employee
	@DeleteMapping("/employees/{id}")
	public Map<String,Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException{
		Employee emp = this.empRepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Record not found for id : "+employeeId));
		this.empRepo.delete(emp);
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted", true);
		return response;
	}
}
