package com.crm.customer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.customer.dto.SearchDataTable;
import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.CompanyAddressDetails;
import com.crm.customer.service.CompanyAddressDetailsService;

@RestController
@RequestMapping("/addressDetails")
public class CompanyAddressDetailsController {

	@Autowired
	CompanyAddressDetailsService companyAddressDetailsService;

	@PostMapping("/datatable")
	public ResponseEntity<Page<CompanyAddressDetails>> addressDetailsDataTable(
			@RequestBody SearchDataTable searchDataTable) {

		Page<CompanyAddressDetails> response = companyAddressDetailsService.addressDetailsDataTable(searchDataTable);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody CompanyAddressDetails companyAddressDetails) {

		CompanyAddressDetails response = companyAddressDetailsService.save(companyAddressDetails);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/findbyid/{id}")
	public ResponseEntity<CompanyAddressDetails> getAddressDetailById(@PathVariable(value = "id") Long id) {
		Optional<CompanyAddressDetails> response = companyAddressDetailsService.getAddressDetailById(id);

		if (response.isPresent()) {
			return new ResponseEntity<>(response.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Company Address Details not found ");
		}
	}

	@PutMapping("/update")
	public ResponseEntity<CompanyAddressDetails> update(@RequestBody CompanyAddressDetails companyAddressDetails) {
		try {
			CompanyAddressDetails response = companyAddressDetailsService.update(companyAddressDetails);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Company Address Details not found ");
		}

	}

}
