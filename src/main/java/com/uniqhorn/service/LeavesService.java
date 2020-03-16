package com.uniqhorn.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniqhorn.dao.LeavesDAO;
import com.uniqhorn.dao.UserDAO;
import com.uniqhorn.dto.AllLeaves;
import com.uniqhorn.dto.UserLeaves;
import com.uniqhorn.entity.Leave;
import com.uniqhorn.entity.LeaveStatus;
import com.uniqhorn.entity.LeaveStatuses;
import com.uniqhorn.entity.LeaveType;
import com.uniqhorn.entity.LeaveUpdate;
import com.uniqhorn.entity.User;
import com.uniqhorn.repository.LeaveStatusesRepository;
import com.uniqhorn.utils.Validator;

import javassist.NotFoundException;


@Service
public class LeavesService {

    Validator validator = new Validator();
    public Leave theLeave;

    @Autowired
    private LeavesDAO leavesDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private LeaveStatusesRepository leaveStatusesRepository;

    @Autowired
    GetLeaveStatus getLeaveStatus;

    @Autowired
    private GetLoggedUserService getLoggedUserService;

    // Create Leave
    public Leave createLeave(Leave leave) {
        return leavesDAO.save(leave);
    }

	/*
	 * //Get all leaves public List<Leave> getAllLeaves() { return
	 * leavesDAO.findAll(); }
	 */
    
  //Get all leaves
    public List<AllLeaves> getAllLeaves() {
       List<Leave> leaves = leavesDAO.findAll();
       
       List<AllLeaves> allLeaves = new ArrayList<AllLeaves>();

       for(Leave leave : leaves) {
    	   AllLeaves responseLeave = new AllLeaves();
    	   responseLeave.setUsername(leave.getUser().getUsername());
    	   responseLeave.setLeave(leave);
    	   allLeaves.add(responseLeave);
       }

    	return allLeaves;
    
    }

    /**
     * This method is getting a User when the parameter is passed in the URL The
     * method will be changed (probably), to send the username via JSON Object
     */
    // Get Leave By Id
    public Leave getLeaveById(long id) throws NotFoundException {
        Leave leave = leavesDAO.findById(id);
        if (leave == null) {
            throw new NotFoundException("Leave not found");
        } else
            return leave;
    }

    // Delete Leave by Id
    public void deleteById(long uid_fk) {
        leavesDAO.deleteById(uid_fk);
    }

    /**
     * This method creates leave for a given user (given username) and returns
     * username + leave
     */
    public UserLeaves createLeaveByUsername(String username, Leave leave) throws IllegalArgumentException {
        User user = userDAO.findByUserName(username);
        if (user == null) {
            throw new NoSuchElementException("User not found");
        } else if (!validator.isCorrectLeaveHours(leave.getHours())) {
            throw new IllegalArgumentException("You can enter only 4 or 8 hours for leave");
        } else if (validator.isWeekend(leave.getLeaveDate())) {
            throw new IllegalArgumentException("You can't enter a leave for Saturday or Sunday!");
            // return new ResponseEntity<>("You can't enter a leave for Saturday or
            // Sunday!", HttpStatus.BAD_REQUEST);
        } else if (validator.isLeavePaid(leave) && !validator.userHasEnoughPaidLeave(leave, user)) {
            throw new IllegalArgumentException("You don`t have enough days left for paid leave, your left days are : "
                    + user.getLeftLeavesHours() / 8 + "days!");
        } else if (validator.isLeaveUnpaid(leave) && !validator.userHasEnoughUnpaidLeave(leave, user)) {
            throw new IllegalArgumentException("You don`t have enough days left for unpaid leave, your left days are : "
                    + user.getUnpaidLeave() / 8 + "days!");
        } else if (validator.getLeaveHoursForTheDay(user, leave.getLeaveDate()) + leave.getHours() > 8) {
            throw new IllegalArgumentException("You can't enter more than 8 hours of leave for one day!");
        } else if (validator.getWorkHoursForTheDay(user, leave.getLeaveDate()) > 4) {
            throw new IllegalArgumentException("You can't have > 4 hours of work and a leave for the day!");
        } else if (validator.isMedicTypeLeave(leave.getLeaveType()) == true && validator.isTheMedicalLeaveIs8h(leave.getHours()) == false) {
            throw new IllegalArgumentException(
                    "Medical leave can only be 8 hours, you have entered " + leave.getHours() + " hours!");
        } else {
            //If leave is type PAID take Days
            //Set status and created by
            if (validator.isLeavePaid(leave)) {
                Leave newLeave = createNewLeave(leave, user);
                // Set leave status Pending
                LeaveStatuses lstatus = getLeaveStatus.getLeaveStatus(LeaveStatus.PENDING);
                newLeave.setLeaveStatus(lstatus);

                // Set created_by
                newLeave.setCreated_by(getLoggedUserService.getLoggedUser());

                updateLeaveLeftHours(newLeave);
                return createResponseForNewLeave(user);

                //IF leave is UNPAID take days
                //Set status and created by
            } else if (validator.isLeaveUnpaid(leave)) {
                Leave newLeave = createNewLeave(leave, user);
                // Set leave status Pending
                LeaveStatuses lStatus = getLeaveStatus.getLeaveStatus(LeaveStatus.PENDING);
                newLeave.setLeaveStatus(lStatus);
                // Set created_by
                newLeave.setCreated_by(getLoggedUserService.getLoggedUser());
                updateUnpaidLeaveLeftHours(newLeave);
                return createResponseForNewLeave(user);

                //else (SICK, OTHER, SPECIAL) just
                //Set status and created by
            } else {
                Leave newLeave = createNewLeave(leave, user);
                LeaveStatuses lstatus = getLeaveStatus.getLeaveStatus(LeaveStatus.PENDING);
                newLeave.setLeaveStatus(lstatus);
                newLeave.setCreated_by(getLoggedUserService.getLoggedUser());
                updateSickSpecialOtherLeave(newLeave);
                return createResponseForNewLeave(user);
            }
        }
    }

    public UserLeaves createResponseForNewLeave(User user) {
        UserLeaves response = new UserLeaves();
        Set<Leave> userLeaves = leavesDAO.getUserLeaves(user);
        response.setUsername(user.getUsername());
        response.setLeaves(userLeaves);
        return response;
    }


    public Leave createNewLeave(Leave leave, User user) {
        Leave newLeave = new Leave(leave, user);
        leavesDAO.save(newLeave);
        return newLeave;
    }

    /***
     * This method gets the leaves for a particular user by given username and
     * returns the data in the right format - username:leaves
     */
    public UserLeaves getUserLeaves(String username) {
        User user = userDAO.findByUserName(username);
        if (user == null)
            throw new NoSuchElementException("Username is not found");
        Set<Leave> userLeaves = leavesDAO.getUserLeaves(user);
        UserLeaves response = new UserLeaves();
        response.setUsername(user.getUsername());
        response.setLeaves(userLeaves);
        return response;
    }

    // delete all leaves by username
    public void deleteByUser(User user) {
        Set<Leave> leaves = user.getLeaves();
        leaves.clear();
        leavesDAO.deleteByUser(user);
        System.out.println(leaves.size());

    }

    public void deleteByLeaveId(int id) {
        Leave leave = leavesDAO.findById(id);
        User user = leave.getUser();
        Set<Leave> leaves = user.getLeaves();
        leaves.remove(leave);
        leavesDAO.deleteByUser(user);
    }

    // Update Leaves status
    public void updateLeaveStatus(Leave leave, String status)
            throws JsonMappingException, JsonProcessingException, IOException {

        LeaveStatuses prevStatus = leave.getLeaveStatus();
        LeaveStatus currentStatus = prevStatus.getLeaveStatus();
        System.out.println(currentStatus);
        LeaveType leaveType = leave.getLeaveType();
        ObjectMapper objectMapper = new ObjectMapper();
        LeaveStatus leaveStatus = objectMapper.readValue(status, LeaveStatus.class);
        LeaveStatuses getLeaveStatus = this.getLeaveStatus.getLeaveStatus(leaveStatus);
        leave.setLeaveStatus(getLeaveStatus);

        System.out.println(prevStatus.getLeaveStatus());
        System.out.println(getLeaveStatus.getLeaveStatus());

        // Check if the given status and the current status are the same
        if (leaveStatus.equals(currentStatus)) {
            throw new IllegalArgumentException("The leave is already to the current status: " + getLeaveStatus.getLeaveStatus());
        }

        // Check if leave is paid
        if (leaveType.equals(LeaveType.PAID)) {

            // If leave is declined or canceled return paid leave hours
            if (leaveStatus.equals(LeaveStatus.DECLINED) || (leaveStatus.equals(LeaveStatus.CANCELED))) {
                returnPaidLeaveHours(leave);
                System.out.println("If leave is declined or canceled return unpaid leave hours");
                leavesDAO.save(leave);
            }
            // If leave is declined and submitted again take leave paid hours
            if (currentStatus.equals(LeaveStatus.DECLINED) || currentStatus.equals(LeaveStatus.CANCELED)) {
                updateLeaveLeftHours(leave);
                System.out.println("If leave is declined and submitted again take leave paid hours");
                leavesDAO.save(leave);
            }
            leavesDAO.save(leave);
        }

        // Check if leave is unpaid
        if (leaveType.equals(LeaveType.UNPAID)) {

            // If leave is declined or canceled return unpaid leave hours
            if (leaveStatus.equals(LeaveStatus.DECLINED) || (leaveStatus.equals(LeaveStatus.CANCELED))) {
                returnUnpaidLeaveHours(leave);
                System.out.println("If leave is declined or canceled return unpaid leave hours");
                leavesDAO.save(leave);
            }
            // If leave is declined and submitted again take leave unpaid hours
            if (currentStatus.equals(LeaveStatus.DECLINED) || currentStatus.equals(LeaveStatus.CANCELED)) {
                updateUnpaidLeaveLeftHours(leave);
                System.out.println("If leave is declined and submitted again take leave unpaid hours");
                leavesDAO.save(leave);
            }
            leavesDAO.save(leave);
        }

        //If leave type is SPECIAL, SICK or OTHER just save the status
        leavesDAO.save(leave);
    }

    // Update leaves information
    // this is update only for master
    @Transactional

    public void updateById(long id, LeaveUpdate newLeave) throws IOException {

        theLeave = leavesDAO.findById(id);

        long TheId = theLeave.getId();
        theLeave.setCreated_by(getLoggedUserService.getLoggedUser());
        String leaveDescription = theLeave.getLeaveDescription();
        if (!validator.isCorrectLeaveHours(newLeave.getHours())) {
        	throw new IllegalArgumentException("You can enter only 4 or 8 hours for leave");
        }
        else if (validator.isMedicTypeLeave(newLeave.getLeaveType()) && !validator.isTheMedicalLeaveIs8h(newLeave.getHours())) {
        	throw new IllegalArgumentException("Medical leave can only be 8 hours, you have entered " + newLeave.getHours() + " hours!");
        }
        if (newLeave.getLeaveDescription() != null) {
        	BeanUtils.copyProperties(newLeave, theLeave);
        }
        else {
	        BeanUtils.copyProperties(newLeave, theLeave);
	        theLeave.setLeaveDescription(leaveDescription);
        }
        theLeave.setId(TheId);
        leavesDAO.save(theLeave);
    }

    // Update hours
    // Extract approved leave hours from user left leave hours
    public void updateLeaveLeftHours(Leave leave) {
        User user = leave.getUser();

        int leftHours = user.getLeftLeavesHours();
        int leaveHours = leave.getHours();

        // Check if the user have enough leave hours
        leftHours = leftHours - leaveHours;
        user.setLeftLeavesHours(leftHours);
        userDAO.save(user);
    }

    public void returnPaidLeaveHours(Leave leave) {
        User user = leave.getUser();


        Integer paidLeftHours = user.getLeftLeavesHours();
        Integer paidLeaveHours = leave.getHours();

        // Check if the user have enough leave hours
        paidLeftHours = paidLeftHours + paidLeaveHours;
        user.setLeftLeavesHours(paidLeftHours);
        userDAO.save(user);
    }

    public void updateUnpaidLeaveLeftHours(Leave leave) {
        User user = leave.getUser();

        Integer unpaidLeftHours = user.getUnpaidLeave();
        Integer unpaidLeaveHours = leave.getHours();

        // Check if the user have enough leave hours
        unpaidLeftHours = unpaidLeftHours - unpaidLeaveHours;
        user.setUnpaidLeave(unpaidLeftHours);
        userDAO.save(user);
    }

    public void returnUnpaidLeaveHours(Leave leave) {
        User user = leave.getUser();

        Integer unpaidLeftHours = user.getUnpaidLeave();
        Integer unpaidLeaveHours = leave.getHours();

        // Check if the user have enough leave hours
        unpaidLeftHours = unpaidLeftHours + unpaidLeaveHours;
        user.setUnpaidLeave(unpaidLeftHours);
        userDAO.save(user);
    }

    public void updateSickSpecialOtherLeave(Leave leave) {
        User user = leave.getUser();

        user.getLeftLeavesHours();
        userDAO.save(user);
    }
}
