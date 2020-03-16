package com.uniqhorn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniqhorn.entity.User;
import com.uniqhorn.entity.UserID;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, UserID> {

	User findByUsername(String username);
	
	boolean existsByUsername(String username);

	@Transactional
	void deleteByUsername(String username);

}
