package com.uniqhorn.controller;

import com.uniqhorn.entity.User;
import com.uniqhorn.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.fail;

@RunWith(MockitoJUnitRunner.class)
class UserControllerTest {

	@Mock
	UserService userService;

	@InjectMocks
	UserController userController;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/*
	 * @Test void testCreateUser() { User expectedUser = new User();
	 * ResponseEntity<User> userResponse = ResponseEntity.ok().body(expectedUser);
	 * Mockito.doReturn(userResponse).when(userService.createUser(any())); //
	 * when(userService.createUser(any()))//.thenReturn((ResponseEntity<?>) //
	 * userResponse);
	 * Mockito.when(this.userService.createUser(any())).thenReturn((ResponseEntity<?
	 * >) userResponse); ResponseEntity<?> actualUserResponse =
	 * this.userController.createUser(expectedUser);
	 * assertThat(actualUserResponse.getStatusCode(), is(HttpStatus.OK));
	 * assertThat(actualUserResponse.getBody(), is(equalTo(expectedUser))); }
	 * 
	 * @Test void testCreateUserInvalid() { User expectedUser = new User();
	 * ResponseEntity<User> userResponse = ResponseEntity.badRequest().build();
	 * when(userService.createUser(any())).thenReturn(userResponse); ResponseEntity
	 * actualUserResponse = this.userController.createUser(expectedUser);
	 * assertThat(actualUserResponse.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	 * // assertThat(actualUserResponse.getBody(), is(equalTo(expectedUser))); }
	 */

	@Test
	void testGetAllUsers() {
		fail("Not yet implemented");
	}

	@Test
	void testGetUserByUsername() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteByUsername() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateByUsername() {
		fail("Not yet implemented");
	}

}
