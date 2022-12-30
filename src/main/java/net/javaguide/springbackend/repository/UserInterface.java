package net.javaguide.springbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguide.springbackend.model.Users;


public interface UserInterface extends JpaRepository<Users,Long> {
	public Users findByEmail(String email);
//	public boolean auth(String email, String password);
	
}
