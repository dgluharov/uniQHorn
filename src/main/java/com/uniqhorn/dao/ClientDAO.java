package com.uniqhorn.dao;

import com.uniqhorn.entity.Client;
import com.uniqhorn.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientDAO {

	@Autowired
	ClientRepository clientRepository;

	public Client save(Client client) {
		return clientRepository.save(client);
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	public Client findByClientName(String clientName) {
		return clientRepository.findByClientName(clientName);
	}

	public void deleteClient(String clientName) {
		clientRepository.deleteByClientName(clientName);

	}

	public void deleteById(long clientId) {
		clientRepository.deleteById(clientId);
	}

	/*
	 * public Client findById(long clientId) { return
	 * clientRepository.findById(clientId).get(); }
	 */

	public Optional<Client> findById(long clientId) {
		return clientRepository.findById(clientId);
	}

}
