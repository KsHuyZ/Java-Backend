package net.javaguide.springbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguide.springbackend.model.Permission;
import net.javaguide.springbackend.repository.PermissonRepository;


@CrossOrigin("*")
@RestController
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissonRepository permissionRepository;
	
	@PostMapping("/create-permisson")
	public Permission createPermission(@RequestBody Permission newPermission) {
		Permission permission = new Permission();
		permission.setName(newPermission.getName());
		return permissionRepository.save(permission);
		
	}
}
