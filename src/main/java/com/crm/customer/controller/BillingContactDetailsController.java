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
import com.crm.customer.model.BillingContactDetails;
import com.crm.customer.service.BillingContactDetailsService;


@RestController
@RequestMapping("billing-contact-details")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BillingContactDetailsController {

	@Autowired
	BillingContactDetailsService billingContactDetailsService;
	
	@GetMapping("all")
	public ResponseEntity<Page<BillingContactDetails>> getSearchAndPagination(@Nullable String name, String owner, Long billingAccountId,
			Pageable pageable) {
		Page<BillingContactDetails> billingContactDetailsPage = billingContactDetailsService.getSearchAndPagination(name,owner,billingAccountId, pageable);
		return new ResponseEntity<>(billingContactDetailsPage, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<BillingContactDetails> getById(@PathVariable(value = "id") Long id) {
		Optional<BillingContactDetails> billingContactDetailsOptional = billingContactDetailsService.getById(id);
		if (billingContactDetailsOptional.isPresent()) {
			return new ResponseEntity<>(billingContactDetailsOptional.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Billing Contact Details not found.");
		}
	}
	
	@PostMapping(path = "create")
	public ResponseEntity<BillingContactDetails> create(@RequestBody BillingContactDetails billingContactDetails){
		try {
			BillingContactDetails billingContactDetailsSaved = billingContactDetailsService.create(billingContactDetails);
			return new ResponseEntity<>(billingContactDetailsSaved, HttpStatus.OK);
		} catch (DataIntegrityViolationException dive) {
			throw new DataIntegrityViolationException("Data IntegrityViolationException" + dive);
		} catch (Exception e) {
			throw new PersistenceException("Failed saving Billing Contact Details.", e);
		}
	}
	
	@PutMapping(path = "update")
	public ResponseEntity<BillingContactDetails> update(@RequestBody BillingContactDetails billingContactDetails){
		try {
			BillingContactDetails billingContactDetailsUpdate = billingContactDetailsService.update(billingContactDetails);
			return new ResponseEntity<>(billingContactDetailsUpdate, HttpStatus.OK);
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage());
		}
	}
	
	@DeleteMapping("softdelete/{id}/{updatedBy}")
	public ResponseEntity<BillingContactDetails> softDelete(@PathVariable Long id, @PathVariable String updatedBy) {
		BillingContactDetails BillingContactDetails = billingContactDetailsService.softDelete(id, updatedBy);
		return new ResponseEntity<>(BillingContactDetails, HttpStatus.OK);
	}
}
