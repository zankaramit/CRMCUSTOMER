package com.crm.customer.controller;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.BillingAccount;
import com.crm.customer.service.BillingAccountService;

@RestController
@RequestMapping("billing-account")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BillingAccountController {

	@Autowired
	BillingAccountService billingAccountService;

	@GetMapping("all")
	public ResponseEntity<Page<BillingAccount>> getSearchAndPagination(@Nullable String name, String owner, Long customerId,
			Pageable pageable) {
		Page<BillingAccount> billingAccountPage = billingAccountService.getSearchAndPagination(name,owner, customerId, pageable);
		return new ResponseEntity<>(billingAccountPage, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<BillingAccount> getById(@PathVariable(value = "id") Long id) {
		Optional<BillingAccount> billingAccountOptional = billingAccountService.getById(id);
		if (billingAccountOptional.isPresent()) {
			return new ResponseEntity<>(billingAccountOptional.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Billing Account not found.");
		}
	}

	@PostMapping(path = "create")
	public ResponseEntity<BillingAccount> create(@RequestBody BillingAccount billingAccount) {
		try {
			BillingAccount billingAccountSaved = billingAccountService.create(billingAccount);
			return new ResponseEntity<>(billingAccountSaved, HttpStatus.OK);
		} catch (DataIntegrityViolationException dive) {
			throw new DataIntegrityViolationException("Data IntegrityViolationException" + dive);
		} catch (Exception e) {
			throw new PersistenceException("Failed saving Billing Account.", e);
		}
	}

	@PutMapping(path = "update")
	public ResponseEntity<BillingAccount> update(@RequestBody BillingAccount billingAccount) {
		try {
			BillingAccount billingAccountUpdate = billingAccountService.update(billingAccount);
			return new ResponseEntity<>(billingAccountUpdate, HttpStatus.OK);
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	@DeleteMapping("softdelete/{id}/{updatedBy}")
	public ResponseEntity<BillingAccount> softDelete(@PathVariable Long id, @PathVariable String updatedBy) {
		BillingAccount billingAccount = billingAccountService.softDelete(id, updatedBy);
		return new ResponseEntity<>(billingAccount, HttpStatus.OK);
	}
}
