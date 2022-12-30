package net.javaguide.springbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguide.springbackend.model.Permission;


public interface PermissonRepository extends JpaRepository<Permission,Long>{

}
