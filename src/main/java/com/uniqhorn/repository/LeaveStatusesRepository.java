package com.uniqhorn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniqhorn.entity.LeaveStatus;
import com.uniqhorn.entity.LeaveStatuses;



public interface LeaveStatusesRepository extends JpaRepository<LeaveStatuses, Integer> {

	boolean existsByLeaveStatus(LeaveStatus leaveStatus);
	
	LeaveStatuses findByLeaveStatus(LeaveStatus leaveStatus);
}
