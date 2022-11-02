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
import com.crm.customer.model.CreditCardPaymentDetails;
import com.crm.customer.service.CreditCardPaymentService;

@RestController
@RequestMapping("credit-card-payment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CreditCardPaymentController {

	@Autowired
	CreditCardPaymentService creditCardPaymentService;

	@GetMapping("{id}")
	public ResponseEntity<CreditCardPaymentDetails> getById(@PathVariable(value = "id") Long id) {
		Optional<CreditCardPaymentDetails> creditCardPaymentDetailsOptional = creditCardPaymentService.getById(id);
		if (creditCardPaymentDetailsOptional.isPresent()) {
			return new ResponseEntity<>(creditCardPaymentDetailsOptional.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Credit Card Payment Details not found.");
		}
	}

	@PostMapping(path = "create")
	public ResponseEntity<CreditCardPaymentDetails> create(
			@RequestBody CreditCardPaymentDetails creditCardPaymentDetails) {
		try {
			CreditCardPaymentDetails creditCardPaymentDetailsSaved = creditCardPaymentService
					.create(creditCardPaymentDetails);
			return new ResponseEntity<>(creditCardPaymentDetailsSaved, HttpStatus.OK);
		} catch (DataIntegrityViolationException dive) {
			throw new DataIntegrityViolationException("Data IntegrityViolationException" + dive);
		} catch (Exception e) {
			throw new PersistenceException("Failed saving Credit Card Payment Details.", e);
		}
	}

	@PutMapping(path = "update")
	public ResponseEntity<CreditCardPaymentDetails> update(
			@RequestBody CreditCardPaymentDetails creditCardPaymentDetails) {
		try {
			CreditCardPaymentDetails creditCardPaymentDetailsUpdate = creditCardPaymentService
					.update(creditCardPaymentDetails);
			return new ResponseEntity<>(creditCardPaymentDetailsUpdate, HttpStatus.OK);
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	@DeleteMapping("softdelete/{id}/{updatedBy}")
	public ResponseEntity<CreditCardPaymentDetails> softDelete(@PathVariable Long id, @PathVariable String updatedBy) {
		CreditCardPaymentDetails creditCardPaymentDetails = creditCardPaymentService.softDelete(id, updatedBy);
		return new ResponseEntity<>(creditCardPaymentDetails, HttpStatus.OK);
	}

}
