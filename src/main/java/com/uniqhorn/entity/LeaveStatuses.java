package com.uniqhorn.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Leave_Statuses")
public class LeaveStatuses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leave_status_id")
	private int leaveStatusId;

	@Enumerated(EnumType.STRING)
	private LeaveStatus leaveStatus;

	// One to many relationship with Leave
	/*
	 * @OneToMany(targetEntity = Leave.class, mappedBy = "leave", fetch =
	 * FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	 * 
	 * @JsonIgnore private Set<Leave> leaves;
	 */

	public LeaveStatuses(LeaveStatus leaveStatus) {
		this.leaveStatus = leaveStatus;
	}
}
