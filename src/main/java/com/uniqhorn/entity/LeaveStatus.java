package com.uniqhorn.entity;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LeaveStatus {
	// PENDING, APPROVED, DENIED, CANCELED, CONFIRMED;

	PENDING(1), APPROVED(2), DECLINED(3), CANCELED(4), CONFIRMED(5); 
	
	

	int val;

	LeaveStatus(int val) {
		this.val = val;
	}

	@JsonValue
	public int toValue() {
		return ordinal();
	}

	
	LeaveStatus() {
		
	}
}
