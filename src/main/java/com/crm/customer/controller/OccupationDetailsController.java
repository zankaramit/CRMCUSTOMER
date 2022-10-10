package com.crm.customer.controller;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.OccupationDetails;
import com.crm.customer.service.OccupationDetailsService;

@RestController
@RequestMapping("occupation-details")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OccupationDetailsController {

	@Autowired
	OccupationDetailsService occupationDetailsService;

	@PostMapping(path = "create")
	public ResponseEntity<OccupationDetails> create(@RequestBody OccupationDetails occupationDetails) {

		try {
			OccupationDetails occupationSaved = occupationDetailsService.save(occupationDetails);
			return new ResponseEntity<>(occupationSaved, HttpStatus.OK);

		} catch (DataIntegrityViolationException dive) {
			throw new DataIntegrityViolationException("Data IntegrityViolationException" + dive);

		} catch (Exception e) {
			throw new PersistenceException("Failed saving Occupation Details.", e);

		}
	}

	@GetMapping("{id}")
	public ResponseEntity<OccupationDetails> getById(@PathVariable(value = "id") Long id) {
		Optional<OccupationDetails> occupationOpt = occupationDetailsService.getById(id);
		if (occupationOpt.isPresent()) {
			return new ResponseEntity<>(occupationOpt.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Occupation not found.");
		}
	}

	@PutMapping(path = "update")
	public ResponseEntity<OccupationDetails> update(@RequestBody OccupationDetails occupationDetails) {
		try {
			OccupationDetails occupationUpdate = occupationDetailsService.update(occupationDetails);
			return new ResponseEntity<>(occupationUpdate, HttpStatus.OK);
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage());
		}

	}

	@DeleteMapping("softdelete/{id}/{updatedBy}")
	public ResponseEntity<OccupationDetails> softDelete(@PathVariable Long id, @PathVariable String updatedBy) {
		OccupationDetails occupationDeleted = occupationDetailsService.softDelete(id, updatedBy);
		return new ResponseEntity<>(occupationDeleted, HttpStatus.OK);
	}
}
