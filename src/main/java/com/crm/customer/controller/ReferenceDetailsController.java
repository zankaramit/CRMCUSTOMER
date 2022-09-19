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
import com.crm.customer.model.ReferenceDetails;
import com.crm.customer.service.ReferenceDetailsService;

@RestController
@RequestMapping("/referenceDetails")
public class ReferenceDetailsController {

	@Autowired
	ReferenceDetailsService referenceDetailsService;

	@PostMapping("/datatable")
	public ResponseEntity<Page<ReferenceDetails>> referenceDetailsDataTable(
			@RequestBody SearchDataTable searchDataTable) {

		Page<ReferenceDetails> response = referenceDetailsService.referenceDetailsDataTable(searchDataTable);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ReferenceDetails referenceDetails) {

		ReferenceDetails response = referenceDetailsService.save(referenceDetails);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<ReferenceDetails> update(@RequestBody ReferenceDetails referenceDetails) {

		try {
			ReferenceDetails response = referenceDetailsService.update(referenceDetails);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Reference details not found  ");
		}
	}

	@GetMapping("/findbyid/{id}")
	public ResponseEntity<ReferenceDetails> getReferenceDetailsById(@PathVariable(value = "id") Long id) {

		Optional<ReferenceDetails> response = referenceDetailsService.getReferenceDetailsById(id);

		if (response.isPresent()) {
			return new ResponseEntity<>(response.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Reference  Details not found ");
		}
	}

}
