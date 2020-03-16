package com.uniqhorn.utils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniqhorn.entity.LeaveStatus;
import com.uniqhorn.entity.LeaveStatuses;
import com.uniqhorn.entity.Role;
import com.uniqhorn.entity.User;
import com.uniqhorn.repository.LeaveStatusesRepository;
import com.uniqhorn.repository.RoleRepository;
import com.uniqhorn.service.UserService;


@Component
public class DbInit {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    LeaveStatusesRepository leaveStatusesRepository;

    /**
     * This method creates admin user in DB when the app starts
     */
    @PostConstruct
    private void createAdmin() throws Exception {
        // Create User start date
        Date date = FormatDate.parseDate("2018-03-21");
        // Create User role ADMIN
        Role role = new Role("ADMIN");
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);


        User admin = new User("admin@gmail.com", "Admin1", "Admin", "Admin",
				"Admin", "Office", "0888112233", date, 20, 20);
        admin.setRoles(roles);

        // Check if admin with that username allready exists
        boolean exists = userService.existsByUsername("admin@gmail.com");

        // Save to DB
        if (!exists) {
            userService.createUser(admin);
        }
    }

    /**
     * This method creates the roles in DB when the application starts
     */

    @PostConstruct
    public void createRoles() {
        // create Roles
        Role admin = new Role("ADMIN");
        Role user = new Role("USER");
        Role master = new Role("MASTER");

        // Check if the roles already exists in DB
        boolean existsAdmin = roleRepository.existsByRole("ADMIN");
        System.out.println(existsAdmin);
        boolean existsUser = roleRepository.existsByRole("USER");
        boolean existsMaster = roleRepository.existsByRole("MASTER");

        // Save to DB
        if (!existsAdmin) {
            roleRepository.save(admin);
        }

        if (!existsUser) {
            roleRepository.save(user);
        }

        if (!existsMaster) {
            roleRepository.save(master);
        }
    }

    @PostConstruct
    public void createLeaveStatuses() {
        // create LeaveStatus - enums
        LeaveStatuses pending = new LeaveStatuses(LeaveStatus.PENDING);
        LeaveStatuses approved = new LeaveStatuses(LeaveStatus.APPROVED);
        LeaveStatuses declined = new LeaveStatuses(LeaveStatus.DECLINED);
        LeaveStatuses canceled = new LeaveStatuses(LeaveStatus.CANCELED);
        LeaveStatuses confirmed = new LeaveStatuses(LeaveStatus.CONFIRMED);

        // save pending to db
        if (!leaveStatusesRepository.existsByLeaveStatus(LeaveStatus.PENDING)) {
            leaveStatusesRepository.save(pending);
        }

        // save approved to db
        if (!leaveStatusesRepository.existsByLeaveStatus(LeaveStatus.APPROVED)) {
            leaveStatusesRepository.save(approved);
        }

        // save declined to db
        if (!leaveStatusesRepository.existsByLeaveStatus(LeaveStatus.DECLINED)) {
            leaveStatusesRepository.save(declined);
        }

        // save canceled to db
        if (!leaveStatusesRepository.existsByLeaveStatus(LeaveStatus.CANCELED)) {
            leaveStatusesRepository.save(canceled);
        }

        // save confirmed to db
        if (!leaveStatusesRepository.existsByLeaveStatus(LeaveStatus.CONFIRMED)) {
            leaveStatusesRepository.save(confirmed);
        }
    }
}
