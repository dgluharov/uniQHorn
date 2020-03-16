package com.uniqhorn.utils;

/**
 * This Class will be used create ADMIN User automatically
 * */

import com.uniqhorn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

	private UserRepository userRepository;
	
	@Autowired
	public DataLoader(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
