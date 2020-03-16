package com.uniqhorn.service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import javax.naming.directory.InvalidAttributesException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniqhorn.dao.ClientDAO;
import com.uniqhorn.dao.UserDAO;
import com.uniqhorn.dao.WorkDAO;
import com.uniqhorn.entity.Client;
import com.uniqhorn.entity.User;
import com.uniqhorn.entity.Work;
import com.uniqhorn.utils.Validator;

import javassist.NotFoundException;

@Service
public class WorkService {

    @Autowired
    WorkDAO workDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    ClientDAO clientDAO;

    @Autowired
    GetLoggedUserService getLoggedUserService;

    Validator validator = new Validator();

    // Create work for User
    public Work createWorkByUsername(String username, Work work) throws InvalidAttributesException, NotFoundException {
        Date date = work.getWorkDate();
        User user = userDAO.findByUserName(username);
        Work newWork = new Work(work, user);
        long clientId = work.getClient().getClient_id();
        Optional<Client> optionalClient = clientDAO.findById(clientId);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            newWork.setClient(client);
        } else {
            throw new NotFoundException("Invalid client");
        }

        // Check if user exists in DB
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        if (!validator.isCorrectWorkHours(work.getWorkHours())) {
            throw new NotFoundException(
                    "Work can only be between 0.25 and 24 hours, you have entered " + work.getWorkHours() + " hours!");
        } else if (!(validator.getWorkHoursForTheDay(user, date) + work.getWorkHours() <= 24)) {
            throw new InvalidAttributesException(
                    "Work can only be between 0.25 and 24 hours, you have entered " + work.getWorkHours() + " hours!");
        } else if (validator.getLeaveHoursForTheDay(user, date) > 4) {
            throw new InvalidAttributesException("You can`t have more than 4 hours of leave and work on the same day");
        } else if (!validator.isValidWorkType(work.getWorkType())) {
            throw new InvalidAttributesException("Invalid work type");
        } else {
            setCreatedByIdForWork(newWork);
            workDAO.save(newWork);
            return work;
        }
    }

    // Get User Work
    // Throws NotFoundException exception, if user not found
    public Set<Work> getUserWork(String username) throws NotFoundException {
        User user = userDAO.findByUserName(username);
        // Check if user exists in DB
        if (user == null) {
            throw new NotFoundException("User not found");
        } else {
            return user.getWork();
        }
    }

    // Get Work by Id
    // Throws NotFoundException
    public Work getWorkById(int workId) throws NotFoundException {
        Work work = workDAO.findById(workId);
        if (work == null) {
            throw new NotFoundException("Work not found");
        } else {
            return work;
        }
    }

    // delete Work by id
    // Throws NotFoundException
    public void deleteWork(int workId) throws NotFoundException {
        Work work = workDAO.findById(workId);
        if (work == null) {
            throw new NotFoundException("Work not found");
        } else {
            workDAO.deleteById(workId);
        }

    }

    // Update Work
    // Throws NotFoundException
    public Work updateWork(long workId, Work work) throws NotFoundException {
        Work oldWork = workDAO.findById(workId);

        if (oldWork == null) {
            throw new NotFoundException("Work not found");
        } else {
            User getUser = oldWork.getUser();

            BeanUtils.copyProperties(work, oldWork);
            oldWork.setId(workId);
            oldWork.setUser(getUser);
            setCreatedByIdForWork(oldWork);
            long clientId = work.getClient().getClient_id();
            Optional<Client> optionalClient = clientDAO.findById(clientId);

            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                oldWork.setClient(client);
                workDAO.save(oldWork);
                return oldWork;
            } else {
                throw new NotFoundException("Invalid client");
            }
        }
    }

    public void setCreatedByIdForWork(Work work) {
        work.setCreated_by(getLoggedUserService.getLoggedUser());
    }
}
