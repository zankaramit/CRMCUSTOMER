package com.crm.customer.controller;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crm.customer.dto.SearchCustomer;
import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.Customer;
import com.crm.customer.service.CustomerService;

@RestController
@RequestMapping("customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Value("${profilePhotoSize}")
	private Long profilePhotoSize;

	@GetMapping("all")
	public ResponseEntity<Page<Customer>> getByNameAndPagination(@Nullable String name, String owner,
			Pageable pageable) {

		Page<Customer> customerPage = customerService.getByNameCustomerAndPagination(name, owner, pageable);
		return new ResponseEntity<>(customerPage, HttpStatus.OK);

	}

	@GetMapping("{id}")
	public ResponseEntity<Customer> getById(@PathVariable(value = "id") Long id) {
		Optional<Customer> customerOptional = customerService.getById(id);

		if (customerOptional.isPresent()) {
			return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Customer not found.");
		}

	}

	@PostMapping(path = "create")
	public ResponseEntity<Customer> create(@RequestPart Customer customer, @Nullable @RequestPart MultipartFile file) {
		if (file != null) {
			if (!file.getOriginalFilename().isEmpty() && file.getSize() > profilePhotoSize) {
				throw new PersistenceException("Profile photo size should be less than " + profilePhotoSize + "Bytes");
			}
		}
		try {
			Customer customerSaved = customerService.save(customer, file);
			return new ResponseEntity<>(customerSaved, HttpStatus.OK);
		} catch (DataIntegrityViolationException dive) {
			throw new DataIntegrityViolationException("Data IntegrityViolationException" + dive);
		} catch (Exception e) {
			throw new PersistenceException("Failed saving customer.", e);
		}
	}

	@PutMapping(path = "update")
	public ResponseEntity<Customer> update(@RequestPart Customer customer, @Nullable @RequestPart MultipartFile file) {
		if (file != null) {
			if (!file.getOriginalFilename().isEmpty() && file.getSize() > profilePhotoSize) {
				throw new PersistenceException("Profile photo size should be less than " + profilePhotoSize + "Bytes");
			}
		}
		try {
			Customer customerUpdate = customerService.update(customer, file);
			return new ResponseEntity<>(customerUpdate, HttpStatus.OK);
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	@DeleteMapping("softdelete/{id}/{updatedBy}")
	public ResponseEntity<Customer> softDelete(@PathVariable Long id, @PathVariable String updatedBy) {

		Customer customer = customerService.softDelete(id, updatedBy);
		return new ResponseEntity<>(customer, HttpStatus.OK);

	}

	@GetMapping("all-parent-account")
	public ResponseEntity<Page<Customer>> getAllCustomerType(@Nullable String name, String customerType,
			Pageable pageable) {

		Page<Customer> customerPage = customerService.getAllByCustomerType(name, customerType, pageable);
		return new ResponseEntity<>(customerPage, HttpStatus.OK);

	}

	@GetMapping("search-customer")
	public ResponseEntity<Page<SearchCustomer>> getCustomer(String searchType, String input, Pageable pageable) {

		Page<SearchCustomer> customerPage = customerService.searchCustomer(searchType, input, pageable);
		return new ResponseEntity<>(customerPage, HttpStatus.OK);

	}

	@GetMapping("customer-details")
	public ResponseEntity<Object> getCustomerValues(@Nullable Long id, String callType) {
		Object obj = customerService.geatAllInfo(id, callType);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
}
