package net.javaguide.springbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguide.springbackend.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	//all crud database methods
	
}
