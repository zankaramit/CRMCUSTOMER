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
import com.crm.customer.model.CompanyDetails;
import com.crm.customer.service.CompanyDetailsService;

@RestController
@RequestMapping("companydetails")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CompanyDetailsController {

	@Autowired
	CompanyDetailsService companyDetailsService;

	@GetMapping("{id}")
	public ResponseEntity<CompanyDetails> getById(@PathVariable(value = "id") Long id) {
		Optional<CompanyDetails> companyDetails = companyDetailsService.getById(id);
		if (companyDetails.isPresent()) {
			return new ResponseEntity<>(companyDetails.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Company Details not found.");
		}
	}

	@PostMapping(path = "create")
	public ResponseEntity<?> create(@RequestBody CompanyDetails companyDetails) {
		try {
			CompanyDetails companyDetailsSaved = companyDetailsService.save(companyDetails);
			return new ResponseEntity<>(companyDetailsSaved, HttpStatus.OK);
		} catch (DataIntegrityViolationException dive) {
			return new ResponseEntity<>(new ErrorResponse("Exception is :" + dive.getRootCause().getMessage()),
					HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			throw new PersistenceException("Failed saving Company Details.", e);
		}
	}

	@PutMapping(path = "update")
	public ResponseEntity<CompanyDetails> update(@RequestBody CompanyDetails companyDetails) {

		try {
			CompanyDetails companyDetailsUpdate = companyDetailsService.update(companyDetails);
			return new ResponseEntity<>(companyDetailsUpdate, HttpStatus.OK);
		} catch (Exception e) {
			throw new PersistenceException("Failed update Company Details.", e);
		}

	}

	@DeleteMapping("softdelete/{id}/{updatedBy}")
	public ResponseEntity<CompanyDetails> softDelete(@PathVariable Long id, @PathVariable String updatedBy) {

		CompanyDetails companyDetails = companyDetailsService.softDelete(id, updatedBy);
		return new ResponseEntity<>(companyDetails, HttpStatus.OK);

	}

}
