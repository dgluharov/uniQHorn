package com.uniqhorn.controller;

import com.uniqhorn.entity.Client;
import com.uniqhorn.service.ClientService;
import com.uniqhorn.utils.Validator;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/client")
public class ClientController {

	@Autowired
	ClientService clientService;

	Validator validator = new Validator();

	/**
	 * @api {post} /client Create client
	 * @apiName createClient
	 * @apiGroup Client
	 * @apiPermission Master
	 * @apiParam {String} clientName Client name.
	 * @apiSuccess {String} clientName Client name
	 * @apiSuccess {Number} id ID
	 * @apiSuccess HTTP200OK ok ok
	 * @apiError HTTP400BadRequest Incorrect data format
	 */
	// TODO Create a client only by MASTER role
	// Catch DataIntegrityViolationException
	@PostMapping
	public ResponseEntity<?> createClient(@RequestBody Client client) {
		if (validator.isValidStartDate(client.getStartDate())) {
			try {
				Client newClient = clientService.createClient(client);

				// Response object
				Map<String, Object> response = new HashMap<String, Object>();
				response.put("id", newClient.getClient_id());
				response.put("clientName", newClient.getClientName());

				return ResponseEntity.ok().body(response);
			} catch (DataIntegrityViolationException ex) {
				if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					return new ResponseEntity<>("The entered Client already exists ", HttpStatus.CONFLICT);
				} else {
					return ResponseEntity.badRequest().body(ex);
				}
			} catch (ConstraintViolationException e) {
				return ResponseEntity.badRequest().body("The request body is invalid");
			}
		} else {
			return new ResponseEntity<>("The entered date is invalid! ", HttpStatus.CONFLICT);
		}
	}

	/**
	 * @api {get} /client Get clients
	 * @apiName getClients
	 * @apiGroup Client
	 * @apiPermission All
	 * @apiSuccess {Number} id id
	 * @apiSuccess {String} clientName clientName
	 * @apiSuccess HTTP200OK ok ok
	 */
	// Get all clients
	@GetMapping
	public ResponseEntity<?> getAllClients() {
		List<Client> clients = clientService.getAllClients();
		return ResponseEntity.ok().body(clients);
	}

	/**
	 * @api {get} /client/clientName Get client
	 * @apiName getClient
	 * @apiGroup Client
	 * @apiPermission All
	 * @apiParam {String} clientName url param - Client name.
	 * @apiSuccess {Number} id id
	 * @apiSuccess {String} clientName clientName
	 * @apiSuccess HTTP200OK ok ok
	 */
	// Get client by clientName
	@GetMapping("/{clientName}")
	public ResponseEntity<?> getClientByName(@PathVariable String clientName) {
		try {
			Client client = clientService.getClientByName(clientName);

			// Response
			Map<String, Object> response = new HashMap<String, Object>();
			response.put("id", client.getClient_id());
			response.put("clientName", client.getClientName());
			return ResponseEntity.ok().body(response);
		} catch (

		NotFoundException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	/**
	 * @api {delete} /client/clientName Delete client
	 * @apiName deleteClient
	 * @apiGroup Client
	 * @apiPermission All
	 * @apiParam {Number} clientName url param - Client id.
	 * @apiSuccess {String} message Client deleted
	 * @apiSuccess HTTP200OK ok ok
	 */
	// Delete client:
	@DeleteMapping("/{clientId}")
	public ResponseEntity<?> deleteClient(@PathVariable Integer clientId) {
		try {
			clientService.deleteClient(clientId);
			return ResponseEntity.ok().body("Client deleted");
		} catch (NotFoundException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}

	}

	/**
	 * @api {patch} /client patch client
	 * @apiName patchClient
	 * @apiGroup Client
	 * @apiPermission Master
	 * @apiParam {String} clientName Client name.
	 * @apiSuccess {String} clientName Client name
	 * @apiSuccess {Number} id ID
	 * @apiSuccess HTTP200OK ok ok
	 * @apiError HTTP400BadRequest Incorrect data format
	 */
	// update client
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateClient(@PathVariable long id, @RequestBody Client client) {
		try {

			// Check if input is correct (no blank fields for name and date)
			String clientName = client.getClientName().trim();
			Date clientDate = client.getStartDate();
			if ((clientName.length() < 1) || (clientDate == null)) {
				return ResponseEntity.badRequest().body("The request body is invalid");
			} else {
				Client newClient = clientService.updateClient(id, client);
				return ResponseEntity.ok().body(newClient);
			}

			// Check if client with that name already exists
		} catch (DataIntegrityViolationException ex) {
			if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				return new ResponseEntity<>("The entered Client already exists ", HttpStatus.CONFLICT);
			} else {
				return ResponseEntity.badRequest().body(ex);
			}
		} catch (NotFoundException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}

	}
}
