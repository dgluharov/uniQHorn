package com.uniqhorn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniqhorn.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	boolean existsByRole(String role);

	Role findByRole(String role);
	

}
