package com.crm.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.customer.dto.Status;
import com.crm.customer.model.CompanyDetails;
import com.crm.customer.service.CompanyDetailsService;

@RestController
@RequestMapping("/companydetails")
public class CompanyDetailsController {

	@Autowired
	CompanyDetailsService companyDetailsService;
	
	
//	@PostMapping("/save")
//	public ResponseEntity<Status> save(@RequestBody CompanyDetails companyDetails){
//		
//		ResponseEntity<Status> response = new ResponseEntity<>( companyDetailsService.save(companyDetails), HttpStatus.OK);
//		return response;
//		
//	}
}
