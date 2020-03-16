package com.uniqhorn.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//Composite primary key for users

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class UserID implements Serializable {
	
	private long id;
	
	private String userName; //Email
	
	
}

