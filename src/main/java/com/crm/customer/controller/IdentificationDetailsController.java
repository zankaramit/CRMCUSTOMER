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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.Identification;
import com.crm.customer.service.IdentificationDetailsService;

@RestController
@RequestMapping("identification-details")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class IdentificationDetailsController {

	@Autowired
	IdentificationDetailsService identificationDetailsService;
	
	@GetMapping("all")
	public ResponseEntity<Page<Identification>> getSearchAndPagination(@Nullable String name, Long customerId,
			Pageable pageable) {
		Page<Identification> identificationPage = identificationDetailsService.getSearchAndPagination(name, customerId, pageable);
		return new ResponseEntity<>(identificationPage, HttpStatus.OK);
	}
	
	@PostMapping(path = "create")
	public ResponseEntity<Identification> create(@RequestPart Identification identification, @Nullable @RequestPart MultipartFile file){
		try {
		Identification identificationSaved = identificationDetailsService.save(identification, file);
		return new ResponseEntity<>(identificationSaved, HttpStatus.OK);
		}catch (DataIntegrityViolationException dive) {
			throw new DataIntegrityViolationException("Data IntegrityViolationException"+dive);

		} catch (Exception e) {
			throw new PersistenceException("Failed saving Identification Details.", e);
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Identification> getById(@PathVariable(value = "id") Long id) {
		Optional<Identification> identificationOptional = identificationDetailsService.getById(id);
		if (identificationOptional.isPresent()) {
			return new ResponseEntity<>(identificationOptional.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Identification Details not found.");
		}

	}
	
	@PutMapping(path = "update")
	public ResponseEntity<Identification> update(@RequestPart Identification identification,@Nullable @RequestPart MultipartFile file){
		try {
			Identification identificationUpdate = identificationDetailsService.update(identification, file);
			return new ResponseEntity<>(identificationUpdate, HttpStatus.OK);
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage());
		}
	}
	
	@DeleteMapping("softdelete/{id}/{updatedBy}")
	public ResponseEntity<Identification> softDelete(@PathVariable Long id, @PathVariable String updatedBy) {
		Identification identificationDeleted =identificationDetailsService.softDelete(id, updatedBy);
				return new ResponseEntity<>(identificationDeleted, HttpStatus.OK);
	}
}
