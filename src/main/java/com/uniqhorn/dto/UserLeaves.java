package com.uniqhorn.dto;

import java.util.Set;

import com.uniqhorn.entity.Leave;

import lombok.Data;

@Data
public class UserLeaves {
	
	private String username;
	private Set<Leave> leaves;
}
