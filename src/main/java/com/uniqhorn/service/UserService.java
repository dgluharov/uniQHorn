package com.uniqhorn.service;

import com.uniqhorn.controller.UserController;
import com.uniqhorn.entity.UserUpdate;

import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.transaction.Transactional;

import com.uniqhorn.repository.RoleRepository;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.uniqhorn.dao.LeavesDAO;
import com.uniqhorn.dao.UserDAO;
import com.uniqhorn.entity.Role;
import com.uniqhorn.entity.User;
import com.uniqhorn.entity.UserRole;
import com.uniqhorn.utils.Validator;

@Service
public class UserService {

Validator validator = new Validator();
public User theUser;

@Autowired
private UserDAO userDAO;

@Autowired
private RoleRepository roleRepository;

@Autowired
private BCryptPasswordEncoder passwordEncoder;

@Autowired
private GetLoggedUserService getLoggedUserService;

// Create User

public User createUser(User user) throws DataIntegrityViolationException, InvalidFormatException {
    // This method validate all fields
	User userExist = userDAO.findByUserName(user.getUsername());
	if (userExist != null) {
		throw new DataIntegrityViolationException("User already exists. Please enter an email address that is not currently used.");
	}
    boolean isAllFieldsValid = validator.validateAllFields(user);
    if (isAllFieldsValid) {
        // Encode PWD
        String pwd = user.getPassword();
        String encryptPwd = passwordEncoder.encode(pwd);
        user.setPassword(encryptPwd);
        setCreatedByIdForUser(user);
        Set<Role> userRoles = user.getRoles();
        boolean existAdmin = userRoles.contains(new Role("ADMIN"));
        List<String> dbRoles = Arrays.asList("ADMIN", "USER", "MASTER");
        for (String role : dbRoles) {
            if (userRoles.contains(role)) {
                Role dbRole = roleRepository.findByRole(role);
                user.getRoles().add(dbRole);
            }
        }
        userDAO.save(user);
        return user;
    } else {
        throw new DataIntegrityViolationException("The create data you've entered is invalid or incomplete");
    }
}
	
	//Method to create first Admin user
	public void createAdmin(User user) {
		String pwd = user.getPassword();
		String encryptPwd = passwordEncoder.encode(pwd);
		user.setPassword(encryptPwd);
		setCreatedByIdForUser(user);
		
		userDAO.save(user);
	}

	// Get All Users+
	public List<User> getAllUsers() {
		return userDAO.findAll();
	}

	/**
	 * This method is getting a User when the parameter is passed in the URL The
	 * method will be changed (probably), to send the username via JSON Object
	 */
	// Get User By Username
	public User getUserByUsername(String username) throws NotFoundException {
		User user = userDAO.findByUserName(username);
		if (user == null) {
			throw new NotFoundException("User search data is not found");
		}
		return user;
	}

	/*
	 * // Delete User by Username public void deleteByUsername(String username)
	 * throws NotFoundException { User user = userDAO.findByUserName(username);
	 * 
	 * if (user == null) { // return ResponseEntity.notFound().build(); throw new
	 * NotFoundException("User not found"); } else if (user.getDeleted() != 0) {
	 * throw new NotFoundException("User not found"); } else {
	 * //userDAO.deleteByUsername(username); user.setDeleted(1); userDAO.save(user);
	 * } }
	 */

	// Delete User by Username
	public void deleteByUsername(String username) throws NotFoundException {
		User user = userDAO.findByUserName(username);

		if (user == null) {
			throw new NotFoundException("User delete data is not found");
		} else {
			 userDAO.deleteByUsername(username);
		}
	}

	
	@Transactional
	public UserUpdate updateByUsername(String username, UserUpdate newUser) throws NotFoundException {
		User user = userDAO.findByUserName(username);
		if (user == null) {
			throw new NotFoundException("User update data is not found");
		} else {
			boolean isAllFieldsValid = validator.validateAllFieldsUpdateUser(newUser);
			if (isAllFieldsValid == true) {
				
			//	Set<Role> newRole = newUser.getRoles();
				
				// Encode PWD
				//String pwd = newUser.getPassword();
				//String encryptPwd = passwordEncoder.encode(pwd);
				//newUser.setPassword(encryptPwd);
				theUser = userDAO.findByUserName(username);
				Long TheId = theUser.getId();
				theUser.setCreated_by(getLoggedUserService.getLoggedUser());
				BeanUtils.copyProperties(newUser, theUser);
				theUser.setId(TheId);
			
				return newUser;
			} else {
				throw new DataIntegrityViolationException("The update data you've entered is invalid or incomplete");
			}
		}
	}
	
	@Transactional
	public UserUpdate selfUpdate (String username, UserUpdate newUser) throws NotFoundException {
		User user = userDAO.findByUserName(username);
		if (user == null) {
			throw new NotFoundException("User update data is not found");
		} else {
			if (validator.validateAllFieldsSelfUpdateUser(newUser) == true) {
				theUser = userDAO.findByUserName(username);
				Long TheId = theUser.getId();
				theUser.setFirstName(newUser.getFirstName());
				theUser.setLastName(newUser.getLastName());
				theUser.setPhoneNumber(newUser.getPhoneNumber());
				theUser.setCreated_by(getLoggedUserService.getLoggedUser());
				theUser.setId(TheId);
				return newUser;
			} else {
				throw new DataIntegrityViolationException("The update data you've entered is invalid or incomplete");
			}
		}	
	}
	

	// Check if user exists in db by username
	public boolean existsByUsername(String username) {
		return userDAO.existsByUsername(username);
	}
	
	public void setCreatedByIdForUser (User user)
	{
		
		user.setCreated_by(getLoggedUserService.getLoggedUser());
	}

	// Change User Password By Username and Password
	public void changePasswordForUser(User user, String newPwd) throws IOException, NotFoundException {
		String encryptPwd = passwordEncoder.encode(newPwd);
		user.setPassword(encryptPwd);
		userDAO.save(user);
	}

	// Reset User Password By Username
	public void resetPasswordForUser(User user) throws MessagingException, NotFoundException {
		Random random = new Random();
		String pwd = random.ints(48, 123) // characters from '0' to 'z'
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(10)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		sendResetPasswordEmail(user.getUsername(),pwd);
		String encryptPwd = passwordEncoder.encode(pwd);
		user.setPassword(encryptPwd);
		userDAO.save(user);
	}

	private void sendResetPasswordEmail(String email, String pwd) throws MessagingException {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("uniqhorn.pass.res@gmail.com", "un1@horN!@#$");
			}
		});
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("uniqhorn.pass.res@gmail.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		message.setSubject("Your UniQHorn password was reset");

		String msg = "Greetings!<br/>" +
				"Your UniQHorn password was reset.<br/>" +
				"Your new password is : " + pwd +"<br/>" +
				"Make sure to change it once you log in.<br/>" +
				"Regards<br/>" +
				"UniQHorn Team :)";

		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(msg, "text/html");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);
		message.setContent(multipart);

		Transport.send(message);
	}
}
