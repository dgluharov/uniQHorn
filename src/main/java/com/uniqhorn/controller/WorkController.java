package com.uniqhorn.controller;

import com.uniqhorn.dto.UserWork;
import com.uniqhorn.entity.Work;
import com.uniqhorn.service.GetLoggedUserService;
import com.uniqhorn.service.WorkService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.InvalidAttributesException;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("api/work")
public class WorkController {

    @Autowired
    WorkService workService;
    @Autowired
    GetLoggedUserService getLoggedUserService;

    /**
     * @api {post} /work/username Log work
     * @apiName logWork
     * @apiGroup Work
     * @apiPermission User
     * @apiParam {String} workDate Work date
     * @apiParam {Number} workHours Work hours
     * @apiParam {String} workType Work type
     * @apiParam {String} client Client
     * @apiSuccess HTTP200OK ok ok
     * @apiError HTTP400BadRequest Incorrect data format
     */
    // Create work by username
    @PostMapping("/{username}")
    public ResponseEntity<?> createWorkByUsername(@PathVariable String username, @RequestBody Work work) {
        try {
            //Get logged user
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            //check if it is the same user
            boolean sameUser = username.equals(auth.getName());
            //check if it is master
            boolean hasMasterRole = auth.getAuthorities().stream()
                    .anyMatch(r -> r.getAuthority().equals("ROLE_MASTER"));


            if (hasMasterRole || sameUser) {
                Work newWork = workService.createWorkByUsername(username, work);

                return ResponseEntity.ok().body(newWork);
            } else {
                return ResponseEntity.status(401).body("Access Denied");
            }

        } catch (InvalidAttributesException | NotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("The request body is invalid");
        }
    }


    /**
     * @api {get} /work/username Get work
     * @apiName getWork
     * @apiGroup Work
     * @apiPermission User
     * @apiSuccess HTTP200OK ok ok
     * @apiError HTTP400BadRequest Incorrect data format
     */

    // Get User Work - MASTER
    // or the same user
    // else - access denied
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserWork(@PathVariable String username) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            // check if it is the same user
            boolean sameUser = username.equals(auth.getName());
            // check if it is master
            boolean hasMasterRole = auth.getAuthorities().stream()
                    .anyMatch(r -> r.getAuthority().equals("ROLE_MASTER"));

            if (hasMasterRole || sameUser) {
                Set<Work> userWork = workService.getUserWork(username);
                UserWork response = new UserWork();
                response.setUsername(username);
                response.setWork(userWork);
                return ResponseEntity.ok().body(response);
            } else {
                return ResponseEntity.status(401).body("Access Denied");
            }
        } catch (NotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /**
     * @throws NotFoundException 
     * @api {delete} /work/:id Delete work
     * @apiName deleteWork
     * @apiGroup Work
     * @apiPermission User
     * @apiSuccess HTTP200OK ok ok
     * @apiError HTTP400BadRequest Incorrect data format
     */
    // Delete Work for a User - by leaveID
    @DeleteMapping("/{workId}")
    public ResponseEntity<?> deleteWork(@PathVariable int workId) throws NotFoundException {
    	
    	boolean isSameUser = getLoggedUserService.isSameUser(workService.getWorkById(workId).getUser().getUsername());
        boolean isMasterRole = getLoggedUserService.isMasterUser();
        
        try {
        	if (isMasterRole || isSameUser) {
	            workService.deleteWork(workId);
	            return ResponseEntity.ok().body("Work deleted");
        	}
        	else {
        		return ResponseEntity.status(401).body("Access Denied");
        	}
        } catch (NotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NoSuchElementException el) {
            return ResponseEntity.badRequest().body("Work is not found");
        }

    }

    /**
     * @api {patch} /work/:id Patch work
     * @apiName patchWork
     * @apiGroup Work
     * @apiPermission User
     * @apiSuccess HTTP200OK ok ok
     * @apiError HTTP400BadRequest Incorrect data format
     */
    // Update Work by ID
    @PatchMapping("/{workId}")
    public ResponseEntity<?> updateWork(@PathVariable int workId, @RequestBody Work work) {
        try {
            Work dbWork = workService.getWorkById(workId);
            String username = dbWork.getUser().getUsername();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            // check if it is the same user
            boolean sameUser = username.equals(auth.getName());
            // check if it is master
            boolean hasMasterRole = auth.getAuthorities().stream()
                    .anyMatch(r -> r.getAuthority().equals("ROLE_MASTER"));

            if (hasMasterRole || sameUser) {
                Work updatedWork = workService.updateWork(workId, work);
                return ResponseEntity.ok().body(updatedWork);
            } else {
                return ResponseEntity.status(401).body("Access Denied");
            }

        } catch (NotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}