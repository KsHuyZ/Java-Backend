package net.javaguide.springbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguide.springbackend.model.Permission;
import net.javaguide.springbackend.model.Role;
import net.javaguide.springbackend.repository.RoleRepository;
import net.javaguide.springbackend.services.RoleServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;
	
	@PostMapping("/create-role")
	private Role createRole(@RequestBody Role newrole) {
	return roleRepository.save(newrole);
	}
	@GetMapping("/roles")
	private List<Role> getAllRoles () {
		return roleRepository.findAll();
	}
}
