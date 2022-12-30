package net.javaguide.springbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.javaguide.springbackend.model.Role;
import net.javaguide.springbackend.repository.RoleRepository;

public class RoleServices {

	@Autowired
	private RoleRepository roleRepository;
	
	public Role createNewRole(Role role) {
		return roleRepository.save(role);
	}
	public List<Role> getAllRole() {
		return roleRepository.findAll();
	}
	
}
