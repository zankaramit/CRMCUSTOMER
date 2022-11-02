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
import com.crm.customer.model.InvoiceDetails;
import com.crm.customer.service.InvoiceDetailsService;

@RestController
@RequestMapping("invoice-details")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InvoiceDetailsController {

	@Autowired
	InvoiceDetailsService invoiceDetailsService;

	@GetMapping("{id}")
	public ResponseEntity<InvoiceDetails> getById(@PathVariable(value = "id") Long id) {

		Optional<InvoiceDetails> invoiceDetailsOptional = invoiceDetailsService.getById(id);
		if (invoiceDetailsOptional.isPresent()) {
			return new ResponseEntity<>(invoiceDetailsOptional.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Invoice Details not found.");
		}
	}

	@PostMapping(path = "create")
	public ResponseEntity<InvoiceDetails> create(@RequestBody InvoiceDetails invoiceDetails) {
		try {
			InvoiceDetails invoiceDetailsSaved = invoiceDetailsService.create(invoiceDetails);
			return new ResponseEntity<>(invoiceDetailsSaved, HttpStatus.OK);
		} catch (DataIntegrityViolationException dive) {
			throw new DataIntegrityViolationException("Data IntegrityViolationException" + dive);
		} catch (Exception e) {
			throw new PersistenceException("Failed saving Invoice Details.", e);
		}
	}

	@PutMapping(path = "update")
	public ResponseEntity<InvoiceDetails> update(@RequestBody InvoiceDetails invoiceDetails) {
		try {
			InvoiceDetails invoiceDetailsUpdate = invoiceDetailsService.update(invoiceDetails);
			return new ResponseEntity<>(invoiceDetailsUpdate, HttpStatus.OK);
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	@DeleteMapping("softdelete/{id}/{updatedBy}")
	public ResponseEntity<InvoiceDetails> softDelete(@PathVariable Long id, @PathVariable String updatedBy) {
		InvoiceDetails invoiceDetails = invoiceDetailsService.softDelete(id, updatedBy);
		return new ResponseEntity<>(invoiceDetails, HttpStatus.OK);
	}
}
