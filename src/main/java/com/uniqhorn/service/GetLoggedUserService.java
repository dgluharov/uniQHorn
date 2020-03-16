package com.uniqhorn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.uniqhorn.dao.UserDAO;
import com.uniqhorn.entity.User;

@Service
public class GetLoggedUserService {

    @Autowired
    UserDAO userDAO;

    public long getLoggedUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userDAO.findByUserName(auth.getName()).getId();
    }

    public User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(userDAO.findByUserName(auth.getName()));
        try {
            return userDAO.findByUserName(auth.getName());
        } catch (NullPointerException ex) {
            return null;
        }
    }

    //Return if the logged  user is the same
    //By String Username

    public boolean isSameUser(String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return username.equals(auth.getName());
    }

    //Check if the Logged user has a Master Role
    //Return boolean

    public boolean isMasterUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_MASTER"));
    }

    //Check if the Logged user has a ADMIN Role
    //Return boolean

    public boolean isAdminUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
    }

    //Check if the Logged user has a USER Role
    //Return boolean

    public boolean isUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
    }
}
