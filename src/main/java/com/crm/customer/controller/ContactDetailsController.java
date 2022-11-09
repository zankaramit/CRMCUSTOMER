package com.crm.customer.controller;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
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
import com.crm.customer.model.ContactDetails;
import com.crm.customer.service.ContactDetailsService;

@RestController
@RequestMapping("contact-details")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContactDetailsController {

	@Autowired
	ContactDetailsService contactDetailsService;

	@GetMapping("all")
	public ResponseEntity<Page<ContactDetails>> getSearchAndPagination(@Nullable String name,String owner, Long customerId,
			Pageable pageable) {

		Page<ContactDetails> contactPage = contactDetailsService.getSearchAndPagination(name, owner, customerId, pageable);

		return new ResponseEntity<>(contactPage, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<ContactDetails> getById(@PathVariable(value = "id") Long id) {

		Optional<ContactDetails> contactDetails = contactDetailsService.getById(id);

		if (contactDetails.isPresent()) {
			return new ResponseEntity<>(contactDetails.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Contact Details not found.");
		}
	}

	@PostMapping(path = "create")
	public ResponseEntity<ContactDetails> create(@RequestBody ContactDetails contactDetails) {
		try {
			ContactDetails contactSaved = contactDetailsService.save(contactDetails);
			return new ResponseEntity<>(contactSaved, HttpStatus.OK);
		} catch (DataIntegrityViolationException dive) {
			throw new DataIntegrityViolationException("Data IntegrityViolationException" + dive);

		} catch (Exception e) {
			throw new PersistenceException("Failed saving Contact Details.", e);
		}
	}

	@PutMapping(path = "update")
	public ResponseEntity<ContactDetails> update(@RequestBody ContactDetails contactDetails) {

		try {
			ContactDetails contactUpdate = contactDetailsService.update(contactDetails);
			return new ResponseEntity<>(contactUpdate, HttpStatus.OK);
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	@DeleteMapping("softdelete/{id}/{updatedBy}")
	public ResponseEntity<ContactDetails> softDelete(@PathVariable Long id, @PathVariable String updatedBy) {

		ContactDetails contactDeleted = contactDetailsService.softDelete(id, updatedBy);
		return new ResponseEntity<>(contactDeleted, HttpStatus.OK);

	}
}
