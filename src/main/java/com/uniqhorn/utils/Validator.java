package com.uniqhorn.utils;

import com.uniqhorn.dao.UserDAO;
import com.uniqhorn.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    @Autowired
    UserDAO userDAO;

    public Set<Role> returnValidRoles() {
        Set<Role> validRoles = new HashSet<Role>();
        validRoles.add(new Role("ADMIN"));
        validRoles.add(new Role("MASTER"));
        validRoles.add(new Role("USER"));
        return validRoles;
    }

    // =================================================================================================================

    private Pattern pattern;
    private Matcher matcher;

    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])[0-9a-zA-Z\\d@#!$%&\\-\\_]{6,20}$";

    public Validator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    // Validate password with regular expression
    public boolean isValidPass(final String password) {
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
    // =================================================================================================================

    // Email validator
    public boolean isValidMail(String email) {
        String regex = 	"^[-a-zA-Z0-9_]+(\\.[-a-zA-Z0-9_]+)*@([a-zA-Z0-9_][-a-zA-Z0-9_]*(\\.[-a-zA-Z0-9_]+)*" +
						"\\.(aero|arpa|biz|com|coop|edu|gov|info|int|bg|museum|name|net|org|pro|travel|mobi|" +
						"[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$";
        return email.matches(regex);
    }

    //==================================================================================================================
    // Phone validator
    public boolean isValidPhoneNumber(String phoneNumber) {
        String regex = 	"^\\+?\\(\\d[-]\\d{1,4}\\)[-\\s]?\\d{3,4}[-\\s]?\\d{3,4}[-\\s]?\\d{3,4}$|^\\+?\\(\\d{1,4}\\)" +
						"[-\\s]?\\d{3,4}[-\\s]?\\d{3}[-\\s]?\\d{3}$|^\\+?\\d{1,4}[-\\s]\\d{3,4}[-\\s]\\d{3,4}[-\\s]" +
						"\\d{3,4}$|^\\+?\\d{6,20}$^\\+?\\d{1,4}[-\\s]\\d{3,4}[-\\s]\\d{3,4}$|^\\(\\d\\d{2}\\)[-\\s]" +
						"?\\d{3}[-\\s]?\\d{3,4}|\\(\\d\\)[-\\s]\\(\\d{1,3}\\)[-\\s]?\\d{3}[-\\s]?\\d{3,4}|^\\+?" +
						"\\d{1,2}[-\\s]?\\d{2,4}[-\\s]?\\d{3,4}[-\\s]?\\d{3,4}$";
        return phoneNumber.matches(regex);
    }
    // =================================================================================================================

    // First name validator
    public boolean isValidFirstName(String firstName) {
        String regex = "^(?!-)(?!.*--)[a-zA-z\\-\\s]{2,20}+(?<!-)$";
        return firstName.matches(regex);
    }

    // =================================================================================================================
    // Last name validator
    public boolean isValidLastName(String lastName) {
        String regex = "^(?!-)(?!.*--)[a-zA-z\\-\\s]{2,20}+(?<!-)$";
        return lastName.matches(regex);
    }

    // =================================================================================================================
    // Position validator
    public boolean isValidPosition(String position) {
        String regex = "[a-zA-z\\s]{2,20}";
        return position.matches(regex);
    }

    // =================================================================================================================
    // Status validator
    public boolean isValidStatus(String status) {
        String regex = "[a-zA-z\\s]{2,20}";
        return status.matches(regex);
    }
    // =================================================================================================================
    // Start date validator

    public boolean isValidStartDate(Date startDate) {
        LocalDate currentDate = LocalDate.now();
        Date d1 = new Date("2004/01/01");
        Date d2 = Date.from(currentDate.plusYears(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

		return startDate.after(d1) && startDate.before(d2);
    }

    // =================================================================================================================

    public boolean isValidWorkType(String workType) {
        String regex = "^(?!-)(?!.*--)([^<>\'\"]){3,50}+(?<!-)$";
        return workType.matches(regex);
    }

    // =================================================================================================================
    // LeavesValidator , exist or not exist leave
    public boolean hasLeave(User user, Leave leave) {
        Set<Leave> leaves = new HashSet<Leave>();
        leaves = user.getLeaves();
		return compareLeavesDate(leaves, leave);
	}

    // =================================================================================================================
    public boolean isLeavePaid(Leave leave) {
		return leave.getLeaveType().equals(LeaveType.PAID);
    }

    //==================================================================================================================

    public boolean isLeaveUnpaid(Leave leave) {
		return leave.getLeaveType().equals(LeaveType.UNPAID);
    }

    // =================================================================================================================
    //Check if user has enough day to request a paid leave
    public boolean userHasEnoughPaidLeave(Leave leave, User user) {
		return user.getLeftLeavesHours() >= leave.getHours();
	}

    // =================================================================================================================
    //Check if user has enough unpaid days to request an unpaid leave
    public boolean userHasEnoughUnpaidLeave(Leave leave, User user) {
		return user.getUnpaidLeave() >= leave.getHours();
	}

    // =================================================================================================================
    // Check leave type is it medical(sick)
    public boolean isMedicTypeLeave(Leave leave) {
		return leave.getLeaveType().equals(LeaveType.SICK);
    }

    // =================================================================================================================
    //Check leave hours for medical leave (only 8h)
    public boolean isTheMedicalLeaveIs8h(Leave leave) {
		return leave.getHours() == 8;
    }

    // =================================================================================================================
    // Get the work hours for a user for the passed date
    public double getWorkHoursForTheDay(User user, Date date) {
        Set<Work> userWork = user.getWork();
        double sum = 0;

        for (Work work : userWork) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String stringWorkDate = dateFormat.format(date);
            String stringDate = dateFormat.format(work.getWorkDate());

            if (stringDate.equals(stringWorkDate)) {
                double workHours = work.getWorkHours();
                sum += workHours;
            }
        }
        return sum;
    }

    // =================================================================================================================
    // Get the leave hours for a user for the passed date
    public int getLeaveHoursForTheDay(User user, Date date) {
        Set<Leave> userLeaves = user.getLeaves();
        int sum = 0;

        for (Leave leave : userLeaves) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String stringLeaveDate = dateFormat.format(date);
            String stringDate = dateFormat.format(leave.getLeaveDate());

            if (stringDate.equals(stringLeaveDate)) {
                double leaveHours = leave.getHours();
                sum += leaveHours;
            }
        }
        return sum;
    }

    // =================================================================================================================
    public boolean isCorrectLeaveHours(double leaveHours) {
		return leaveHours == 4.0 || leaveHours == 8.0;
	}

    
	public boolean isValidPaidLeaveHours (int leaveHours){
		if (leaveHours > 800 || leaveHours < -120){
			return false;
		}
		return true;
	}
	
	public boolean isValidPaidLeftLeaveHours (int leftLeaveHours){
		if (leftLeaveHours > 800 || leftLeaveHours < -120){
			return false;
		}
		return true;
	}
	
	 //check totalLeaveHours is more than leftLeaveHours
    public boolean isTotalLeaveHMoreThanLeftLeaveH(int leaveHours, int leftLeaveHours) {
		return leaveHours >= leftLeaveHours;
	}
	
	// ===================================================================================================================
	// Check leave type is it medical(sick)
	public boolean isMedicTypeLeave(LeaveType leaveType) {
		return leaveType.equals(LeaveType.SICK);
	}
	
	// ===================================================================================================================
	//Check leave hours for medical leave (only 8h)
	public boolean isTheMedicalLeaveIs8h(int leaveHours) {
		return leaveHours == 8;
	}
	// ===================================================================================================================
	// LeavesValidator , exist or not exist leave
    public boolean hasLeave(User user, Date date) {
		return getLeaveHoursForTheDay(user, date) > 0;
	}


	// ==================================================================================================================
	// Date leave validator
	private boolean compareLeavesDate(Set<Leave> userLeaves, Leave leave) {
		for (Leave userLeave : userLeaves) {
			Date userDate = userLeave.getLeaveDate();
			Date requestDate = leave.getLeaveDate();
			int equalDates = userDate.compareTo(requestDate);
			if (equalDates == 0) {
				return true;
			}
		}
		return false;
	}

	// =================================================================================================================
	// Role validator
	private boolean isValidRole(Set<Role> role) {
        Set<Role> validRoles = returnValidRoles();
        //Set<Role> roles = new HashSet<Role>();
        //roles = user.getRoles();
        //validRoles = returnValidRoles();

		return validRoles.containsAll(role);
    }

	// ===================================================================================================================
	 public boolean isWeekend(Date date) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);

			return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
					|| calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
		}


	// =================================================================================================================
	// Validate all fields create user
	public boolean validateAllFields(User user) {
		boolean validStatus = isValidStatus(user.getStatus());
		boolean validPosition = isValidPosition(user.getPosition());
		boolean validMail = isValidMail(user.getUsername());
		boolean validPass = isValidPass(user.getPassword());
		boolean validPhone = isValidPhoneNumber(user.getPhoneNumber());
		boolean validFirstName = isValidFirstName(user.getFirstName());
		boolean validLastName = isValidLastName(user.getLastName());
		boolean validRole = isValidRole(user.getRoles());
		boolean validStartDate = isValidStartDate(user.getStartDate());
		boolean validPaidHours = isValidPaidLeaveHours(user.getTotalLeavesHours());
		boolean validPaidLeftHours = isValidPaidLeftLeaveHours(user.getLeftLeavesHours());
		boolean isTLHMTLLH = isTotalLeaveHMoreThanLeftLeaveH(user.getTotalLeavesHours(), user.getLeftLeavesHours());
		
		return validMail && validPass && validPhone && validPosition && validFirstName && validLastName && validStatus
				&& validRole && validStartDate && isTLHMTLLH && validPaidHours && validPaidLeftHours;
	}

//======================================================================================================================
   // Validate all fields update user
    public boolean validateAllFieldsUpdateUser(UserUpdate newUser) {
    	boolean validStatus = isValidStatus(newUser.getStatus());
		boolean validPosition = isValidPosition(newUser.getPosition());
		boolean validMail = isValidMail(newUser.getUsername());
		//boolean validPass = isValidPass(newUser.getPassword());
		boolean validPhone = isValidPhoneNumber(newUser.getPhoneNumber());
		boolean validFirstName = isValidFirstName(newUser.getFirstName());
		boolean validLastName = isValidLastName(newUser.getLastName());
		boolean validRole = isValidRole(newUser.getRoles());
		boolean validStartDate = isValidStartDate(newUser.getStartDate());
		boolean validPaidHours = isValidPaidLeaveHours(newUser.getTotalLeavesHours());
		boolean validPaidLeftHours = isValidPaidLeftLeaveHours(newUser.getLeftLeavesHours());
		boolean isTLHMTLLH = isTotalLeaveHMoreThanLeftLeaveH(newUser.getTotalLeavesHours(), newUser.getLeftLeavesHours());
		
		
		return validMail && validPhone && validPosition && validFirstName && validLastName && validStatus
				&& validRole && validStartDate && isTLHMTLLH && validPaidHours && validPaidLeftHours;
    }

	
	public boolean validateAllFieldsSelfUpdateUser(UserUpdate newUser) {
		boolean validPhone = isValidPhoneNumber(newUser.getPhoneNumber());
		boolean validFirstName = isValidFirstName(newUser.getFirstName());
		boolean validLastName = isValidLastName(newUser.getLastName());
		
		return validPhone && validFirstName && validLastName;
	}
	
	// ======================================================================================================================
	public boolean isCorrectLeaveHours(int leaveHours) {
		if (leaveHours == 4 || leaveHours == 8) {
			return true;
		}
		return false;
	}
	// ======================================================================================================================
	// This method check if the work hours is less than 24 hours and the reminder is 0.0
	  public boolean isCorrectWorkHours(double workHours) {
			return workHours >= 0.25 && workHours <= 24.0 && workHours % 0.25 == 0.0;
	  }

	// ======================================================================================================================
	  public boolean hasWork(User user, Date date) {
			return !(getWorkHoursForTheDay(user, date) <= 0);
	  }
	  
	//catch if error occurs
	public ResponseEntity<?> hasError(Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<>("Invalid request body!", HttpStatus.BAD_REQUEST);
		}else {
			return null;
		}
	}
}
  