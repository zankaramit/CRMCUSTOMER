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
import com.crm.customer.model.ContactDetails;
import com.crm.customer.service.ContactDetailsService;

@RestController
@RequestMapping("/contactDetails")
public class ContactDetailsController {

	@Autowired
	ContactDetailsService contactDetailsService;

	@PostMapping("/datatable")
	public ResponseEntity<Page<ContactDetails>> contactDetailsDataTable(@RequestBody SearchDataTable searchDataTable) {

		Page<ContactDetails> response = contactDetailsService.contactDetailsDataTable(searchDataTable);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ContactDetails contactDetails) {

		ContactDetails response = contactDetailsService.save(contactDetails);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
 
	@PutMapping("/update")
	public ResponseEntity<ContactDetails> update(@RequestBody ContactDetails contactDetails) {

		try {
		ContactDetails response = contactDetailsService.update(contactDetails);
		return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResourceNotFoundException(
					"Contact details not found  ");
		}
	}

	@GetMapping("/findbyid/{id}")
	public ResponseEntity<ContactDetails> getContactDetailsById(@PathVariable(value = "id") Long id) {
		
		Optional<ContactDetails> response = contactDetailsService.getContactDetailsById(id);
		
		if (response.isPresent()) {
			return new ResponseEntity<>(response.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Contact  Details not found ");
		}
	}
	
}
