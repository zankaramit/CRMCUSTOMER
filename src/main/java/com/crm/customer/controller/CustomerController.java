package com.crm.customer.controller;



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
import com.crm.customer.dto.Status;
import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.Customer;
import com.crm.customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/datatable")
	public ResponseEntity<Page<Customer>> customerDataTable(@RequestBody SearchDataTable searchDataTable) {

		ResponseEntity<Page<Customer>> response = new ResponseEntity<>(customerService.findAll(searchDataTable),
				HttpStatus.OK);
		return response;
	}

	@GetMapping("/findbyid/{customerId}")
	public ResponseEntity<Status> customerFindById(@PathVariable(value = "customerId") Long customerId)
			throws ResourceNotFoundException {
		ResponseEntity<Status> response = new ResponseEntity<>(customerService.customerFindById(customerId),
				HttpStatus.OK);
		return response;
	}

	@PostMapping("/save")
	public ResponseEntity<Status> save(@RequestBody Customer customer) {
		ResponseEntity<Status> response = new ResponseEntity<>(customerService.save(customer), HttpStatus.OK);
		return response;
	}

	@PutMapping("/update")
	public ResponseEntity<Status> update(@RequestBody Customer customer) throws ResourceNotFoundException {
		ResponseEntity<Status> response = new ResponseEntity<>(customerService.update(customer), HttpStatus.OK);
		return response;
	}

}
