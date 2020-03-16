package com.uniqhorn.dao;

import com.uniqhorn.entity.User;
import com.uniqhorn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDAO {
	@PersistenceContext
	private EntityManager em;

	@Autowired
	UserRepository userRepository;
	
	// Save User
	public User save(User user) {
		return userRepository.save(user);
	}
	
	//Find All Users
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	//Find User By Username
	public User findByUserName(String username) {
		return userRepository.findByUsername(username);
	}

	//Delete User By Username
	public void deleteByUsername(String username) {
		userRepository.deleteByUsername(username);

	}
	
	// Check if user exists in db by username
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
}
