package com.uniqhorn.dto;

import java.util.Set;

import com.uniqhorn.entity.Work;

import lombok.Data;

@Data
public class UserWork {

	private String username;
	private Set<Work> work;
}
