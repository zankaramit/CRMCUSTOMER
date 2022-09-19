package com.crm.customer.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.customer.dto.SearchDataTable;
import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.Customer;
import com.crm.customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/datatable")
	public ResponseEntity<Page<Customer>> customerDataTable(@RequestBody SearchDataTable searchDataTable) {

		Page<Customer> response = customerService.findAll(searchDataTable);
		return new ResponseEntity<Page<Customer>>(response, HttpStatus.OK);

	}

	@GetMapping("/findbyid/{customerId}")
	public ResponseEntity<Customer> customerFindById(@PathVariable(value = "customerId") Long customerId)
			 {
		Optional<Customer> response = customerService.customerFindById(customerId);

		if (response.isPresent()) {
			return new ResponseEntity<>(response.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Customer not found for this id :: " + customerId);
		}

	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Customer customer) {

		Customer response = customerService.save(customer);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<Customer> update(@RequestBody Customer customer)   {
		try {
			Customer response = customerService.update(customer);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Customer not found for this id :: " + customer.getCustomerId());
		}
		
	}

}
