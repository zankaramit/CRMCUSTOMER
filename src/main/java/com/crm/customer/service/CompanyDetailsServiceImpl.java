package com.crm.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.customer.dto.Status;
import com.crm.customer.model.CompanyDetails;
import com.crm.customer.repository.CompanyDetailsRepository;
@Service
public class CompanyDetailsServiceImpl implements CompanyDetailsService {

	@Autowired
	CompanyDetailsRepository companyDetailsRepository;
	
//	@Override
//	public Status save(CompanyDetails companyDetails) {
//		Status status = new Status();
//		
//		companyDetailsRepository.save(companyDetails);
//		return status;
//	}

}
