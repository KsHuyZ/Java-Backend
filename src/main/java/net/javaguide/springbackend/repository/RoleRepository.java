package net.javaguide.springbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguide.springbackend.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{

}
