package com.uniqhorn.dto;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * 
 * @author erizov
 *
 *This class is used to send a response
 *When user GET his information
 *with USER ROLE
 */

@Data
@ToString
public class UserResponse {
	
	private String username;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String status;
	private Date startDate;
	private Integer leftLeaveHours;
	private Integer totalLeaveHours;
}