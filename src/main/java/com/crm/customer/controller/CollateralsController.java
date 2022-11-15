package com.crm.customer.controller;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crm.customer.model.Collaterals;
import com.crm.customer.service.CollateralsService;

@RestController
@RequestMapping("collaterals")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CollateralsController {

	@Autowired
	CollateralsService collateralsService;

	@PostMapping(path = "create")
	public ResponseEntity<Collaterals> create(@RequestPart Collaterals collaterals,
			@Nullable @RequestPart MultipartFile file) {
		try {
			Collaterals collateralsSaved = collateralsService.save(collaterals, file);
			return new ResponseEntity<>(collateralsSaved, HttpStatus.OK);
		} catch (DataIntegrityViolationException dive) {
			throw new DataIntegrityViolationException("Data IntegrityViolationException" + dive);
		} catch (Exception e) {
			throw new PersistenceException("Failed saving Documents.", e);
		}
	}

	@GetMapping("{refId}/{flag}")
	public ResponseEntity<List<Collaterals>> getByCustomerIdAndFlag(@PathVariable(value = "refId") Long refId,
			@PathVariable(value = "flag") String flag) {
		try {
			List<Collaterals> collateralsList = collateralsService.getByCustomerIdAndFlag(refId, flag);
			return new ResponseEntity<>(collateralsList, HttpStatus.OK);
		} catch (Exception e) {
			throw new PersistenceException("Documents not found.", e);
		}
	}
	
	@DeleteMapping("softdelete/{id}/{updatedBy}")
	public ResponseEntity<Collaterals> softDelete(@PathVariable Long id, @PathVariable String updatedBy) {
		Collaterals collateralsDeleted = collateralsService.softDelete(id, updatedBy);
		return new ResponseEntity<>(collateralsDeleted, HttpStatus.OK);
	}
}
