package com.uniqhorn.controller;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.uniqhorn.dao.LeavesDAO;
import com.uniqhorn.dao.UserDAO;
import com.uniqhorn.dto.AllLeaves;
import com.uniqhorn.entity.Leave;

import com.uniqhorn.entity.LeaveUpdate;
import com.uniqhorn.entity.User;
import com.uniqhorn.service.GetLoggedUserService;
import com.uniqhorn.service.LeavesService;
import com.uniqhorn.utils.Validator;

import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leaves")
public class LeavesController {

    @Autowired
    private LeavesService leavesService;

    @Autowired
    private UserDAO userDAO;

    Validator validator = new Validator();

    @Autowired
    private LeavesDAO leavesDAO;

    @Autowired
    GetLoggedUserService getLoggedUserService;

    /**
     * @api {post} /leaves/create/:username Create Leave
     * @apiName createLeave
     * @apiGroup Leave
     * @apiPermission User
     * @apiParam {Number} hours Hours.
     * @apiParam {String} leaveDate Leave date.
     * @apiParam {String} leaveType leave type.
     * @apiSuccess HTTP200OK ok ok
     * @apiError HTTP400BadRequest Incorrect data format
     */

    // create a leave for a user
    // @PostMapping("/create/{username}")
    @PostMapping(value = "/create/{username}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createLeaveByUsername(@PathVariable String username, @RequestBody Leave leave) {
        // return leavesService.createLeaveByUsername(username, leave);
        try {
            //check for same logged user
            boolean isSameUser = getLoggedUserService.isSameUser(username);
            boolean hasMasterRole = getLoggedUserService.isMasterUser();

            if (hasMasterRole || isSameUser) {
                return ResponseEntity.ok().body(leavesService.createLeaveByUsername(username, leave));
            } else {
                return ResponseEntity.status(401).body("Access for creating leave user is denied");
            }


        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//	//update by user 
//	@PatchMapping("/update/user/{id}")
//	public ResponseEntity<?> updateByUser(@PathVariable(value = "id") Integer id, @RequestBody LeaveUpdate newLeave)
//			throws Exception {
//		try {
//			leavesService.updateByUser(id, newLeave);
//			return ResponseEntity.ok().body("Updated");
//		} catch (Exception e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//		}
//	}

	/**
	 * @api {get} /leaves Get Leave
	 * @apiName getLeaves
	 * @apiGroup Leave
	 * @apiPermission Master
	 *
	 * 
	 * @apiError HTTP400BadRequest Incorrect data format
	 */
	// Get all leaves
	@GetMapping()
	public ResponseEntity<?> getAllLeaves() {
		//check if Master 
		boolean hasMasterRole = getLoggedUserService.isMasterUser();
		
		if(hasMasterRole) {
			//return ResponseEntity.ok().body(leavesService.getAllLeaves());
			
			List<AllLeaves> allLeaves = leavesService.getAllLeaves();
			return ResponseEntity.ok().body(allLeaves);
			
		} else {
			return ResponseEntity.status(401).body("Access for searching leaves users is denied");
		}
		
		// return leavesService.getAllLeaves();
	}

//	/**
//	 * @api {get} /leaves/username Get Leaves for user
//	 * @apiName getLeaves
//	 * @apiGroup Leave
//	 * @apiPermission Master
//	 *
//
//	 * @apiError HTTP400BadRequest Incorrect data format
//	 */
//	// Get Leaves for a given USER
//	@GetMapping("/{username}")
//	public ResponseEntity<?> getUserLeaves(@PathVariable String username) {
//		try {
//			return ResponseEntity.ok().body(leavesService.getUserLeaves(username));
//		} catch (Exception e) {
//=======
//    @Autowired
//    private LeavesService leavesService;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserDAO userDAO;
//
//    @Autowired
//    private LeavesDAO leavesDAO;
//
//    /**
//     * @api {post} /leaves/create/:username Create Leave
//     * @apiName createLeave
//     * @apiGroup Leave
//     * @apiPermission User
//     * @apiParam {Number} hours Hours.
//     * @apiParam {String} leaveDate Leave date.
//     * @apiParam {String} leaveType leave type.
//     * @apiSuccess HTTP200OK ok ok
//     * @apiError HTTP400BadRequest Incorrect data format
//     */
//    // create a leave for a user
//    // @PostMapping("/create/{username}")
//    @PostMapping(value = "/create/{username}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
//            MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<?> createLeaveByUsername(@PathVariable String username, @RequestBody Leave leave) {
//        // return leavesService.createLeaveByUsername(username, leave);
//        try {
//            return ResponseEntity.ok().body(leavesService.createLeaveByUsername(username, leave));
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//        }
//    }
//
//    /**
//     * @api {patch} /leaves/update/:id Create Leave
//     * @apiName createLeave
//     * @apiGroup Leave
//     * @apiPermission User
//     * @apiParam {Number} hours Hours.
//     * @apiParam {String} leaveDate Leave date.
//     * @apiParam {String} leaveType leave type.
//     * @apiSuccess HTTP200OK ok ok
//     * @apiError HTTP400BadRequest Incorrect data format
//     */
//    // Patch - update leave
//    @PatchMapping("/update/{id}")
//    public ResponseEntity<?> updateById(@PathVariable(value = "id") Integer id, @RequestBody LeaveUpdate newLeave)
//            throws Exception {
//        try {
//            leavesService.updateById(id, newLeave);
//            return ResponseEntity.ok().body("Updated");
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//        }
//    }
//
//    /**
//     * @api {get} /leaves Get Leave
//     * @apiName getLeaves
//     * @apiGroup Leave
//     * @apiPermission Master
//     * @apiError HTTP400BadRequest Incorrect data format
//     */
//    // Get all leaves
//    @GetMapping()
//    public ResponseEntity<List<Leave>> getAllLeaves() {
//        return ResponseEntity.ok().body(leavesService.getAllLeaves());
//        // return leavesService.getAllLeaves();
//    }


	/**
	 * @api {get} /leaves/username Get Leaves for user
	 * @apiName getLeaves
	 * @apiGroup Leave
	 * @apiPermission Master
	 * @apiError HTTP400BadRequest Incorrect data format
	 */


	/**
	 * @api {delete} /leaves/username Delete Leaves for user
	 * @apiName deleteLeaves
	 * @apiGroup Leave
	 * @apiPermission Master
	 * @apiError HTTP400BadRequest Incorrect data format
	 */



    /**
     * @api {patch} /leaves/update/:id Create Leave
     * @apiName createLeave
     * @apiGroup Leave
     * @apiPermission User
     * @apiParam {Number} hours Hours.
     * @apiParam {String} leaveDate Leave date.
     * @apiParam {String} leaveType leave type.
     * @apiSuccess HTTP200OK ok ok
     * @apiError HTTP400BadRequest Incorrect data format
     */
    // Patch - update leave
    // update for Master
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable(value = "id") Long id, @RequestBody LeaveUpdate newLeave)

            throws Exception, IOException {

        try {
            Leave leave = leavesService.getLeaveById(id);
            String username = leave.getUser().getUsername();
            System.out.print(leave.getLeaveStatus().getLeaveStatusId());
            //check if it is the same user as the logged user
            boolean isSameUser = getLoggedUserService.isSameUser(username);
            //check for master role
            boolean hasMasterRole = getLoggedUserService.isMasterUser();

            if (hasMasterRole) {
                leavesService.updateById(id, newLeave);
                return ResponseEntity.ok().body("Updated");
            }	else if (isSameUser && leave.getLeaveStatus().getLeaveStatusId() == 1) {
            	leavesService.updateById(id, newLeave);
                return ResponseEntity.ok().body("Updated");
            }
            else {
                return ResponseEntity.status(401).body("Access for updating leave user is denied");
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    /**
     * @api {get} /leaves Get Leave
     * @apiName getLeaves
     * @apiGroup Leave
     * @apiPermission Master
     * @apiError HTTP400BadRequest Incorrect data format
     */
   


    /**
     * @api {get} /leaves/username 
     * @apiName getLeaves
     * @apiGroup Leave
     * @apiPermission Master
     * @apiError HTTP400BadRequest Incorrect data format
     */


    /**
     * @api {delete} /leaves/username Delete Leaves for user
     * @apiName deleteLeaves
     * @apiGroup Leave
     * @apiPermission Master
     * @apiError HTTP400BadRequest Incorrect data format
     */


    // Get Leaves for a given USER
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserLeaves(@PathVariable String username) {
        try {
            //check if it is the same user as the logged user
            boolean isSameUser = getLoggedUserService.isSameUser(username);
            //check for master role
            boolean hasMasterRole = getLoggedUserService.isMasterUser();

            if (hasMasterRole || isSameUser) {
                return ResponseEntity.ok().body(leavesService.getUserLeaves(username));
            } else {
                return ResponseEntity.status(401).body("Access for searching leave user is denied");
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        // return leavesService.getUserLeaves(username);
    }

    /**
     * @api {delete} /leaves/username Delete Leaves for user
     * @apiName deleteLeaves
     * @apiGroup Leave
     * @apiPermission Master
     * @apiError HTTP400BadRequest Incorrect data format
     */
    // TODO only by master
    // Delete all leaves by username
    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteLeaveByUsername(@PathVariable String username) {
        try {
            //check for master role
            boolean hasMasterRole = getLoggedUserService.isMasterUser();

            if (hasMasterRole) {
                User user = userDAO.findByUserName(username);
                leavesService.deleteByUser(user);
                return ResponseEntity.ok().body("All leave for this user are deleted successfully.");
            } else {
                return ResponseEntity.status(401).body("Access for deleting leaves users is denied");
            }


        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @api {delete} /leaves/id Delete Leaves for user
     * @apiName deleteLeaves
     * @apiGroup Leave
     * @apiPermission Master
     * @apiError HTTP400BadRequest Incorrect data format
     */
    // TODO only by MASTER
    // Delete a leave by leaveId
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteByLeaveId(@PathVariable Integer id)
            throws IllegalArgumentException, NullPointerException {
        try {
            //check for master role
            boolean hasMasterRole = getLoggedUserService.isMasterUser();

            if (hasMasterRole) {
                leavesService.deleteByLeaveId(id);
                return ResponseEntity.ok().body("Leave is deleted successfully.");
            } else {
                return ResponseEntity.status(401).body("Access for deleting leave user is denied");
            }


        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NullPointerException e) {
            return new ResponseEntity<>("Leave not found", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @throws NotFoundException
     * @api {patch} /status/id Patch Leaves for user
     * @apiName PatchLeaves
     * @apiGroup Leave
     * @apiPermission User
     * @apiError HTTP400BadRequest Incorrect data format
     */
    // Set leaves status
    @PatchMapping("/status/{id}")
    public ResponseEntity<?> setLeaveStatus(@PathVariable Integer id, @RequestBody String status)
            throws NotFoundException {
        try {
            //check for master role
            boolean hasMasterRole = getLoggedUserService.isMasterUser();

            if (hasMasterRole) {
                Leave leave = leavesService.getLeaveById(id);
                leavesService.updateLeaveStatus(leave, status);
                return ResponseEntity.ok().body(leave);
            } else {
                return ResponseEntity.status(401).body("Access for update [set user status type] is denied");
            }

        } catch (NotFoundException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (JsonMappingException ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid leave status number! " +
                    "Type a valid number between:\n {0} for PENDING,\n {1} for APPROVED,\n {2} " +
                    "for DECLINED,\n {3} for CANCELED,\n {4} for CONFIRMED");
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (IOException ex) {
            Leave leave = leavesService.getLeaveById(id);
            return ResponseEntity.badRequest().
                    body("This leave status is already selected: " + leave.getLeaveStatus());
        }
    }
}
