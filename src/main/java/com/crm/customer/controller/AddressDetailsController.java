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
import com.crm.customer.model.AddressDetails;
import com.crm.customer.service.AddressDetailsService;

@RestController
@RequestMapping("address-details")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AddressDetailsController {

	@Autowired
	AddressDetailsService addressDetailsService;

	@GetMapping("all")
	public ResponseEntity<Page<AddressDetails>> getSearchAndPagination(@Nullable String name, Long customerId,
			Pageable pageable) {

		Page<AddressDetails> addressPage = addressDetailsService.getSearchAndPagination(name, customerId, pageable);
		return new ResponseEntity<>(addressPage, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<AddressDetails> getById(@PathVariable(value = "id") Long id) {
		Optional<AddressDetails> addressOptional = addressDetailsService.getById(id);

		if (addressOptional.isPresent()) {
			return new ResponseEntity<>(addressOptional.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Address not found.");
		}
	}

	@PostMapping(path = "create")
	public ResponseEntity<AddressDetails> create(@RequestBody AddressDetails addressDetails) {
		try {
			AddressDetails addressSaved = addressDetailsService.save(addressDetails);
			return new ResponseEntity<>(addressSaved, HttpStatus.OK);
		} catch (DataIntegrityViolationException dive) {
			throw new DataIntegrityViolationException("Data IntegrityViolationException" + dive);

		} catch (Exception e) {
			throw new PersistenceException("Failed saving Address Details.", e);
		}
	}

	@PutMapping(path = "update")
	public ResponseEntity<AddressDetails> update(@RequestBody AddressDetails addressDetails) {
		try {
			AddressDetails addressUpdate = addressDetailsService.update(addressDetails);
			return new ResponseEntity<>(addressUpdate, HttpStatus.OK);
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage());
		}

	}

	@DeleteMapping("softdelete/{id}/{updatedBy}")
	public ResponseEntity<AddressDetails> softDelete(@PathVariable Long id, @PathVariable String updatedBy) {

		AddressDetails addressDeleted = addressDetailsService.softDelete(id, updatedBy);
		return new ResponseEntity<>(addressDeleted, HttpStatus.OK);

	}

}
