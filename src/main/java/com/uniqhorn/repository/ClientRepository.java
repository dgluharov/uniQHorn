package com.uniqhorn.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniqhorn.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	Client findByClientName(String clientName);

	@Transactional
	void deleteByClientName(String clientName);

}
