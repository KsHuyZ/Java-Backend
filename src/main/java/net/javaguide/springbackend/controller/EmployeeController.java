package net.javaguide.springbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguide.springbackend.exepction.ResourceNotFoundException;
import net.javaguide.springbackend.model.Employee;
import net.javaguide.springbackend.repository.EmployeeRepository;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/")
public class EmployeeController {
	
	
	@Autowired
	private EmployeeRepository employeeRepsitory;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		
		return employeeRepsitory.findAll();
	}
	@PostMapping("/employee")
	public Employee createEmployee(@RequestBody Employee employee) {
		
		return employeeRepsitory.save(employee);
	}
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
		Employee employee = employeeRepsitory.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not exist!"));
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable long id,@RequestBody Employee updateEmployee ){
		Employee employee = employeeRepsitory.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not exist!"));
	employee.setFirstName(updateEmployee.getFirstName());
	employee.setLastName(updateEmployee.getLastName());
	employee.setEmailId(updateEmployee.getEmailId());
	employeeRepsitory.save(employee);
	return ResponseEntity.ok(employee);
	}
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable long id) {
		Employee employee = employeeRepsitory.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not exist!"));
		employeeRepsitory.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
