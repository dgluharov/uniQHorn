package com.uniqhorn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

	/**
	 *	@api {get} /login Log in as an user
	 *  @apiName process
	 *  @apiGroup LogIn
	 *  @apiPermission none
	 *
	 *  @apiHeader {String} Authorization Username and password with Basic auth.
	 *	@apiHeaderExample {json} Header-Example:
	 *	{
	 *		"Authorization": "Basic YWRtaW5AZ21haWwuY29tOkFkbWluMQ=="
	 *	}

	 *
	 *  @apiSuccess {Cookie} Cookie Cookie with SID.
	 *
	 *
	 * 	@apiError HTTP400BadRequest Incorrect data format
	 */
	@GetMapping("/login")
	public String process() {
		return "Logged in successfully";
	}

}
