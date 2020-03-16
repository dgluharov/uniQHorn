package com.uniqhorn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniqhorn.entity.LeaveStatus;
import com.uniqhorn.entity.LeaveStatuses;
import com.uniqhorn.repository.LeaveStatusesRepository;

/**
 * This class gets leave status from DB
 */

@Service
public class GetLeaveStatus {

    @Autowired
    LeaveStatusesRepository leaveStatusesRepository;

    //This method returns LeaveStatuses object
    //Pass LeaveStatus Enum as argument - LeaveStatus.PENDING
    public LeaveStatuses getLeaveStatus(LeaveStatus lStatus) {
        return leaveStatusesRepository.findByLeaveStatus(lStatus);
    }
}
