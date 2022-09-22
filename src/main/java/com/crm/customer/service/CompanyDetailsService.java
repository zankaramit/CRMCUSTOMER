package com.crm.customer.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.CompanyDetails;
import com.crm.customer.repository.CompanyDetailsRepository;

@Service
public class CompanyDetailsService {

	@Autowired
	CompanyDetailsRepository companyDetailsRepository;

	public CompanyDetails save(CompanyDetails companyDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		companyDetails.setIsDeleted(false);
		companyDetails.setCreatedDate(dateTime);
		return companyDetailsRepository.save(companyDetails);

	}

	public Optional<CompanyDetails> getById(Long id) {

		Optional<CompanyDetails> companyDetails = companyDetailsRepository.findByCompanyDetailsIdAndIsDeleted(id,
				false);
		return companyDetails;
	}

	public CompanyDetails update(CompanyDetails companyDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		CompanyDetails existingCompanyDetails = companyDetailsRepository.findById(companyDetails.getCompanyDetailsId())
				.get();
		existingCompanyDetails.setAccountName(companyDetails.getAccountName());
		existingCompanyDetails.setCompanyRegistrationNumber(companyDetails.getCompanyRegistrationNumber());
		existingCompanyDetails.setTaxRegistrationNumber(companyDetails.getTaxRegistrationNumber());
		existingCompanyDetails.setLineOfBusiness(companyDetails.getLineOfBusiness());
		existingCompanyDetails.setSizeOfCompany(companyDetails.getSizeOfCompany());
		existingCompanyDetails.setAnnualRevenue(companyDetails.getAnnualRevenue());
		existingCompanyDetails.setWebsiteDetails(companyDetails.getWebsiteDetails());
		existingCompanyDetails.setParentAccount(companyDetails.getParentAccount());
		existingCompanyDetails.setUpdatedBy(companyDetails.getUpdatedBy());
		existingCompanyDetails.setUpdatedDate(dateTime);
		return companyDetailsRepository.save(existingCompanyDetails);
	}

	public CompanyDetails softDelete(Long id, String updatedBy) {
		LocalDateTime dateTime = LocalDateTime.now();
		CompanyDetails existingCompanyDetails;
		try {
	    existingCompanyDetails = companyDetailsRepository.findById(id).get();
		} catch (Exception e) {
			throw new ResourceNotFoundException("Company Details not found.");
		}
		if (existingCompanyDetails.getIsDeleted()) {
			throw new PersistenceException("Company Details already Deleted");
		}
		existingCompanyDetails.setIsDeleted(true);
		existingCompanyDetails.setUpdatedBy(updatedBy);
		existingCompanyDetails.setUpdatedDate(dateTime);
		return companyDetailsRepository.save(existingCompanyDetails);
	}

}
