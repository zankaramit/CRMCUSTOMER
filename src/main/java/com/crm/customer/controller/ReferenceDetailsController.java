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
import com.crm.customer.model.ReferenceDetails;
import com.crm.customer.service.ReferenceDetailsService;

@RestController
@RequestMapping("reference-details")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReferenceDetailsController {

	@Autowired
	ReferenceDetailsService referenceDetailsService;

	@GetMapping("all")
	public ResponseEntity<Page<ReferenceDetails>> getSearchAndPagination(@Nullable String name, String owner, Long customerId,
			Pageable pageable) {

		Page<ReferenceDetails> referencePage = referenceDetailsService.getSearchAndPagination(name,owner, customerId,
				pageable);
		return new ResponseEntity<>(referencePage, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<ReferenceDetails> getById(@PathVariable(value = "id") Long id) {

		Optional<ReferenceDetails> referenceOptional = referenceDetailsService.getById(id);

		if (referenceOptional.isPresent()) {
			return new ResponseEntity<>(referenceOptional.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Reference Details not found.");
		}
	}

	@PostMapping(path = "create")
	public ResponseEntity<?> create(@RequestBody ReferenceDetails referenceDetails) {
		try {
			ReferenceDetails referenceSaved = referenceDetailsService.save(referenceDetails);
			return new ResponseEntity<>(referenceSaved, HttpStatus.OK);
		} catch (DataIntegrityViolationException dive) {
			return new ResponseEntity<>(new ErrorResponse("Exception is :" + dive.getRootCause().getMessage()),
					HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			throw new PersistenceException("Failed saving Reference Details.", e);
		}
	}

	@PutMapping(path = "update")
	public ResponseEntity<ReferenceDetails> update(@RequestBody ReferenceDetails referenceDetails) {

		try {
			ReferenceDetails referenceUpdate = referenceDetailsService.update(referenceDetails);
			return new ResponseEntity<>(referenceUpdate, HttpStatus.OK);
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	@DeleteMapping("softdelete/{id}/{updatedBy}")
	public ResponseEntity<ReferenceDetails> softDelete(@PathVariable Long id, @PathVariable String updatedBy) {

		ReferenceDetails referenceDeleted = referenceDetailsService.softDelete(id, updatedBy);
		return new ResponseEntity<>(referenceDeleted, HttpStatus.OK);

	}

}
