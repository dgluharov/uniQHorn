package com.uniqhorn.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.uniqhorn.dao.UserDAO;
import com.uniqhorn.dto.UserResponse;
import com.uniqhorn.entity.PasswordUpdate;
import com.uniqhorn.entity.User;
import com.uniqhorn.entity.UserUpdate;
import com.uniqhorn.service.GetLoggedUserService;
import com.uniqhorn.service.UserService;
import com.uniqhorn.utils.Validator;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    GetLoggedUserService getLoggedUserService;

    Validator validator = new Validator();

    /**
     * @api {post} /users Create user
     * @apiName createUser
     * @apiGroup User
     * @apiPermission ADMIN
     * @apiParam {String} username User email.
     * @apiParam {String} password User password.
     * @apiParam {String} firstName First name.
     * @apiParam {String} lastName Last name.
     * @apiParam {String} position Position e.g. QA, SysAdmin, MD.
     * @apiParam {String} status Status e.g. InOffice, InClientOffice-KOSTAL.
     * @apiParam {String} phone Phone number.
     * @apiParam {Date} start Start date.
     * @apiParam {String[]} roles Roles array.
     * @apiParam {Double} totalLeavesDays. totalLeavesDays
     * @apiParam {Double} leftLeavesDays. leftLeavesDays
     * @apiParamExample {json} Request-Example: { "username" : "test2@gmail.com",
     * "password" : "Test123", "firstName": "John", "lastName" :
     * "Doe", "position" : "Tester", "status" : "inOffice",
     * "phoneNumber" : "+359876278593", "startDate" : "2018-01-30",
     * "roles" : [{ "role":"MASTER" }] }
     * @apiSuccess {Number} id id
     * @apiSuccess {String} username username
     * @apiSuccess {String} password password
     * @apiSuccess {String} firstName first name
     * @apiSuccess {String} lastName last name
     * @apiSuccess {String} position position
     * @apiSuccess {String} status status
     * @apiSuccess {String} phoneNumber phone number
     * @apiSuccess {Date} startDate start date
     * @apiSuccess {String[]} roles roles
     * @apiSuccess {Double} totalLeavesDays. totalLeavesDays
     * @apiSuccess {Double} leftLeavesDays. leftLeavesDays
     * @apiError HTTP400BadRequest Incorrect data format
     * @apiError HTTP409Conflict Existing user
     */

    // Save (create) User
    @Secured({"ROLE_MASTER", "ROLE_ADMIN"})
    @PostMapping("/users")
    @ResponseBody
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, Errors errors) {
        validator.hasError(errors);
        try {
            userService.createUser(user);
            return ResponseEntity.ok().body(user);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
            } else {
                return ResponseEntity.badRequest().body(ex.getMessage());
            }
        } catch (InvalidFormatException | NullPointerException | ConstraintViolationException ex) {
            return ResponseEntity.badRequest().body("The request body is invalid");
        }
    }

    /**
     * @api {get} /users Get all users
     * @apiName getAllUsers
     * @apiGroup User
     * @apiPermission ADMIN
     * @apiSuccess {Number} id id
     * @apiSuccess {String} username username
     * @apiSuccess {String} password password
     * @apiSuccess {String} firstName first name
     * @apiSuccess {String} lastName last name
     * @apiSuccess {String} position position
     * @apiSuccess {String} status status
     * @apiSuccess {String} phoneNumber phone number
     * @apiSuccess {Date} startDate start date
     * @apiSuccess {String[]} roles roles
     * @apiSuccessExample {json} Success-Example: [{ "id": 1, "username":
     * "John@gmail.com", "password":
     * "$2a$10$1mnewXZqa0m.V4peRkt2xuniYRC7bxu6.1cjFJ8SNx3MBON80vIky",
     * "firstName": "John", "lastName": "Doe", "position":
     * "Admin", "status": "Office", "phoneNumber": "1232531",
     * "roles": [ { "role_id": 2, "role": "ADMIN" } ], "leaves":
     * [], "startDate": "2018-03-20" }, { "id": 2, "username":
     * "Jane@gmail.com", "password":
     * "$2a$10$1mnewXZqa0m.V4peRkt2xuniYRC7bxu6.1cjFJ8SNx3MBON80vIky",
     * "firstName": "Jane", "lastName": "Doe", "position":
     * "Admin", "status": "Office", "phoneNumber": "1232531",
     * "roles": [ { "role_id": 2, "role": "ADMIN" } ], "leaves":
     * [], "startDate": "2018-03-20" }]
     */
    // Get all users
    @Secured("ROLE_MASTER")
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }
    // Get User by username

    /**
     * @api {get} /users/:username Get a user by username
     * @apiName getUserByUsername
     * @apiGroup User
     * @apiPermission ADMIN
     * @apiDescription This method is getting a User when the parameter is passed in
     * the URL The method will be changed (probably), to send the
     * username via JSON Object
     * @apiSuccess {Number} id id
     * @apiSuccess {String} username username
     * @apiSuccess {String} password password
     * @apiSuccess {String} firstName first name
     * @apiSuccess {String} lastName last name
     * @apiSuccess {String} position position
     * @apiSuccess {String} status status
     * @apiSuccess {String} phoneNumber phone number
     * @apiSuccess {Date} startDate start date
     * @apiSuccess {String[]} roles roles
     * @apiSuccessExample {json} Success-Example: { "id": 1, "username":
     * "John@gmail.com", "password":
     * "$2a$10$1mnewXZqa0m.V4peRkt2xuniYRC7bxu6.1cjFJ8SNx3MBON80vIky",
     * "firstName": "John", "lastName": "Doe", "position":
     * "Admin", "status": "Office", "phoneNumber": "1232531",
     * "roles": [ { "role_id": 2, "role": "ADMIN" } ], "leaves":
     * [], "startDate": "2018-03-20" }
     */
    // Get User by username
    //@Secured({"ROLE_MASTER", "ROLE_ADMIN"})
    @GetMapping("/users/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable(value = "username") String username) {
        try {
            boolean isSameUser = getLoggedUserService.isSameUser(username);
            boolean isUserRole = getLoggedUserService.isUser();
            boolean isMasterRole = getLoggedUserService.isMasterUser();
            boolean isAdminRole = getLoggedUserService.isAdminUser();

            if ((isSameUser) && (isUserRole || isAdminRole)) {
                System.out.println("UserRole Same user");
                User user = userService.getUserByUsername(username);
                UserResponse userResponse = createUserResponse(user);
                return ResponseEntity.ok().body(userResponse);
            } else if (isMasterRole) {
                User findUser = userService.getUserByUsername(username);
                return ResponseEntity.ok().body(findUser);
            } else {
                return ResponseEntity.status(403).body("Access  denied");
            }
        } catch (NotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /**
     * @api {delete} /users/:username Delete a user by username
     * @apiName deleteByUsername
     * @apiGroup User
     * @apiPermission ADMIN
     */
    // Delete
    @Secured({"ROLE_MASTER", "ROLE_ADMIN"})
    @DeleteMapping("/users/{username}")
    public ResponseEntity<?> deleteByUsername(@PathVariable String username) {
        try {
            userService.deleteByUsername(username);
            return ResponseEntity.ok("User Deleted");
        } catch (NotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    //This method is used to create a User Response object
    // when the user makes GET request and has a USER Role
    private UserResponse createUserResponse(User user) {

        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUsername());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setStatus(user.getStatus());
        userResponse.setStartDate(user.getStartDate());
        userResponse.setLeftLeaveHours(user.getLeftLeavesHours());
        userResponse.setTotalLeaveHours(user.getTotalLeavesHours());

        return userResponse;
    }
    
	// Patch
	//@Secured("ROLE_MASTER")
	@PatchMapping("/users/{username}")
	public ResponseEntity<?> updateByUsername(@PathVariable(value = "username") String username,
			@RequestBody UserUpdate newUser) throws IOException {
		// return userService.updateByUsername(username, newUser);
		try {
			boolean isSameUser = getLoggedUserService.isSameUser(username);
			boolean isUserRole = getLoggedUserService.isUser();
			boolean isMasterRole = getLoggedUserService.isMasterUser();
			boolean isAdminRole = getLoggedUserService.isAdminUser();
			if((isSameUser) && (isUserRole || isAdminRole)) {
				userService.selfUpdate(username, newUser);
				return ResponseEntity.ok().body("User updated sucessfully");
			} else if(isMasterRole) {
				userService.updateByUsername(username, newUser);
				return ResponseEntity.ok().body("User updated sucessfully");
			} else {
				return ResponseEntity.status(403).body("Access  denied");
			}
			
			//return ResponseEntity.ok().body(userService.updateByUsername(username, newUser));
		} catch (DataIntegrityViolationException | NotFoundException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}catch (NullPointerException e) {
			return ResponseEntity.badRequest().body("The request body is invalid");
		}catch (ConstraintViolationException e) {
			return ResponseEntity.badRequest().body("The request body is invalid, or some mandatory field is empty");
		}
	}



    //TODO
    // Only by master
    // Set user status - Active - Maternity - Deactive

    // Body is empty
    // Change user password
    @PostMapping("/users/{username}/change-password")
    public ResponseEntity<?> changePassword(@PathVariable(value = "username") String username,
                                            @Valid @RequestBody PasswordUpdate pwd) throws Exception {
        User user = userDAO.findByUserName(username);
        if (user == null) {
            throw new NotFoundException("User not found");
        } else if (!(pwd.getOldPassword().equals(user.getPassword()))) {
            throw new Exception("Old password incorrect");
        } else if (!(pwd.getNewPassword().equals(pwd.getNewPasswordCheck()))) {
            throw new Exception("New password mismatch");
        }
        userService.changePasswordForUser(user, pwd.getNewPassword());
        return ResponseEntity.ok().body("Password changed to : " + pwd.getNewPassword());
    }

    // Reset user password
    @PostMapping("/users/{username}/reset-password")
    public ResponseEntity<?> resetPassword(@PathVariable(value = "username") String username)
			throws NotFoundException, MessagingException {
        User user = userDAO.findByUserName(username);
        if (user == null) {
            throw new NotFoundException("User not found");
        } else userService.resetPasswordForUser(user);
        return ResponseEntity.ok().body("Password reset successfully");
    }
}
