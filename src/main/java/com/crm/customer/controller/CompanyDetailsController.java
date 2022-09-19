package com.crm.customer.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/companydetails")
public class CompanyDetailsController {

	@Autowired
	CompanyDetailsService companyDetailsService;

	@GetMapping("/findbyid/{customerId}")
	public ResponseEntity<CompanyDetails> companyDetailsFindById(
			@PathVariable(value = "customerId") Long customerId) {
		Optional<CompanyDetails> response = companyDetailsService.companyDetailsFindById(customerId);
		if (response.isPresent()) {
			return new ResponseEntity<>(response.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Customer details not found for customer :: " + customerId);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody CompanyDetails companyDetails) {

		CompanyDetails response = companyDetailsService.save(companyDetails);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<CompanyDetails> update(@RequestBody CompanyDetails companyDetails) {

		try {
			CompanyDetails response = companyDetailsService.update(companyDetails);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResourceNotFoundException(
					"Customer details not found for this id :: " + companyDetails.getCompanyDetailsId());
		}
	}
}
