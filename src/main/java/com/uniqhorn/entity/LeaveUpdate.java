package com.uniqhorn.entity;

import java.util.Date;

import javax.persistence.Column;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor

@Setter
@Getter

public class LeaveUpdate extends JSONObject {

	
	@Column(unique = true)
	private Integer hours;
	@JsonFormat(pattern = "yyyy-MM-dd",lenient = OptBoolean.FALSE)
	private Date leaveDate;
	private LeaveType leaveType;
	private String leaveDescription;
	

	public LeaveUpdate(Integer hours, Date leaveDate, LeaveType leaveType) {
	
		this.hours = hours;
		this.leaveDate = leaveDate;
		this.leaveType = leaveType;
	}
	
}
