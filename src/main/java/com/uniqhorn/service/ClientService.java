package com.uniqhorn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniqhorn.dao.ClientDAO;
import com.uniqhorn.dao.UserDAO;
import com.uniqhorn.entity.Client;
import com.uniqhorn.entity.User;

import javassist.NotFoundException;

@Service
public class ClientService {

	@Autowired
	ClientDAO clientDAO;

	@Autowired
	UserDAO userDAO;

	@Autowired
	GetLoggedUserService getLoggedUserService;

	/*
	 * This method creates a new client If the client already exists the method
	 * throws DataIntegrityViolationException
	 */

	public Client createClient(Client client) throws DataIntegrityViolationException {

		setCreatedByIdForClient(client);
		clientDAO.save(client);
		return client;
	}

	// Get All Clients
	public List<Client> getAllClients() {
		return clientDAO.findAll();
	}

	/*
	 * Get Client By Name Throws NotFoundException When client not found
	 */
	public Client getClientByName(String clientName) throws NotFoundException {
		Client client = clientDAO.findByClientName(clientName);

		if (client == null) {
			// return ResponseEntity.notFound().build();
			throw new NotFoundException("Client not found");
		} else {
			return client;
		}

	}

	// Delete client
	// Throws NotFoundException if client not found
	public void deleteClient(Integer id) throws NotFoundException {
		Optional<Client> optionalClient = clientDAO.findById(id);
		if (optionalClient.isPresent()) {
			Client client = optionalClient.get();
			clientDAO.deleteById(id);
		} else {
			throw new NotFoundException("Client not found");
		}

		/*
		 * if (client == null) { throw new NotFoundException("Client not found"); } else
		 * { clientDAO.deleteById(id); }
		 */

	}

	// Update client
	// Throws NotFoundException if client not found
	
	public Client updateClient(long id, Client client)
			throws NotFoundException, DataIntegrityViolationException {
		//Client oldClient = clientDAO.findByClientName(clientName);
		
		Client oldClient = clientDAO.findById(id).get();

		if (oldClient == null) {
			throw new NotFoundException("Client not found");
		} else {

			long clientId = oldClient.getClient_id();
			BeanUtils.copyProperties(client, oldClient);
			setCreatedByIdForClient(oldClient);
			oldClient.setClient_id(clientId);
			clientDAO.save(oldClient);
			return oldClient;
		}

	}

	public void setCreatedByIdForClient(Client client) {

		client.setCreated_by(getLoggedUserService.getLoggedUser());
	}

}
