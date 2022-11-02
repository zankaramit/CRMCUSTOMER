package com.crm.customer.controller;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.crm.customer.model.BankDetails;
import com.crm.customer.service.BankDetailsService;
@RestController
@RequestMapping("bank-details")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BankDetailsController {

	@Autowired
	BankDetailsService bankDetailsService;
	
	@GetMapping("{id}")
	public ResponseEntity<BankDetails> getById(@PathVariable(value = "id") Long id) {
		Optional<BankDetails> BankDetailsOptional = bankDetailsService.getById(id);
		if (BankDetailsOptional.isPresent()) {
			return new ResponseEntity<>(BankDetailsOptional.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Bank Details not found.");
		}
	}
	
	@PostMapping(path = "create")
	public ResponseEntity<BankDetails> create(@RequestBody BankDetails bankDetails){
		try {
			BankDetails bankDetailsSaved = bankDetailsService.create(bankDetails);
			return new ResponseEntity<>(bankDetailsSaved, HttpStatus.OK);
		} catch (DataIntegrityViolationException dive) {
			throw new DataIntegrityViolationException("Data IntegrityViolationException" + dive);
		} catch (Exception e) {
			throw new PersistenceException("Failed saving Bank Details.", e);
		}
	}
	
	@PutMapping(path = "update")
	public ResponseEntity<BankDetails> update(@RequestBody BankDetails bankDetails){
		try {
			BankDetails bankDetailsUpdate = bankDetailsService.update(bankDetails);
			return new ResponseEntity<>(bankDetailsUpdate, HttpStatus.OK);
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage());
		}
	}
	
	@DeleteMapping("softdelete/{id}/{updatedBy}")
	public ResponseEntity<BankDetails> softDelete(@PathVariable Long id, @PathVariable String updatedBy) {
		BankDetails bankDetails = bankDetailsService.softDelete(id, updatedBy);
		return new ResponseEntity<>(bankDetails, HttpStatus.OK);
	}
}
