package com.uniqhorn.entity;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.OptBoolean;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
//@Data
@AllArgsConstructor

//@EqualsAndHashCode(exclude = "User")

//@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "Leaves", uniqueConstraints = {@UniqueConstraint(columnNames = {"leave_id"})})

//Delete Annotations 
//@SQLDelete(sql = "Update leaves SET deleted = 1 where id=?")
//@Where(clause = "deleted != 1")

public class Leave {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leave_id")
	private long id; // Leaves ID

	@NotNull
	@Column(name = "leave_hours")
	public Integer hours;

	@NotNull
	@Column(name = "leave_date")
	@JsonProperty("leaveDate")
	@JsonFormat(pattern = "yyyy-MM-dd",lenient = OptBoolean.FALSE)
	private Date leaveDate;

	// Used to mark deleted objects
	//private int deleted;

	// Leave type - PAID, UNPAID, SICK, SPECIAL
	@NotNull
	@Column(name = "leave_type")
	@Enumerated(EnumType.STRING)
	private LeaveType leaveType;

	// Leave Status PENDING, APPROVED, DENIED

	//Delete JsonDeserialize...it is causingn problems with the POST Request
	//@JsonDeserialize(using = EnumDeserializer.class)

	//Remove this status and make another table
	/*
	 * @Enumerated(EnumType.STRING) private LeaveStatus leaveStatus;
	 */ // Masters option only

	@Column(name = "leave_description")
	private String leaveDescription;
	
	//Time-stamp
	private Timestamp record_timestamp = new Timestamp(System.currentTimeMillis());
	
	//Add a method in the constructor
	//to initialize created_by - logged in user
	
	
	//TODO
	//add relationship with user
	//@NotNull
	//@Column(name = "created_by")
	
	@JsonIgnore
	@OneToOne(cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name="created_by")
	private User created_by;
	/*@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn( name = "created_by", referencedColumnName = "user_id")
	private User created_by;*/
	
	
	@Enumerated(EnumType.STRING)
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "leave_status_id")
	private LeaveStatuses leaveStatus;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	// @JsonBackReference
	@JsonIgnore
	private User user;

	
	//TODO constructors with new variables
	/**
	 * Constructor Status Pending by default NO id - auto-generated NO user option
	 * (for now)
	 */
	public Leave(@NotNull int hours, @NotNull Date leaveDate, @NotNull LeaveType leaveType, String leaveDescription) {
		this.hours = hours;
		this.leaveDate = leaveDate;
		this.leaveType = leaveType;
		this.leaveDescription = leaveDescription;
		//Set leave status to Pending
//		this.leaveStatus = getLeaveStatus.getLeaveStatus(LeaveStatus.PENDING);
	}

	public Leave() {
		// this.leaveStatus = LeaveStatus.PENDING;
	}

	//TODO constructors with new variables
	/**
	 * This Constructor is userd for creating leave for a particular user (By
	 * username)
	 */
	public Leave(Leave leave, User user) {
		this.hours = leave.hours;
		this.leaveDate = leave.leaveDate;
		this.leaveType = leave.leaveType;
		//set leave status to pending
//		this.leaveStatus = getLeaveStatus.getLeaveStatus(LeaveStatus.PENDING);
		this.leaveDescription = leave.leaveDescription;
		this.user = user;

	}
}
