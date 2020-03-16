package com.uniqhorn.dto;

import com.uniqhorn.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

// Pass this as a request in the controller
public class UserRequest {
	
	/**
	 * From UserRequest we can extract the User object
	 * and from User object - extract Leaves object
	 */
	private User user;
}
