package com.crm.customer.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.customer.model.CompanyDetails;
import com.crm.customer.repository.CompanyDetailsRepository;

@Service
public class CompanyDetailsService {

	@Autowired
	CompanyDetailsRepository companyDetailsRepository;

	public CompanyDetails save(CompanyDetails companyDetails) {
		Date date = new Date();
		companyDetails.setIsDeleted(false);
		companyDetails.setCreatedDate(date);
		return companyDetailsRepository.save(companyDetails);

	}

	public Optional<CompanyDetails> getById(Long id) {

		Optional<CompanyDetails> companyDetails = companyDetailsRepository.findByCompanyDetailsIdAndIsDeleted(id,
				false);
		return companyDetails;
	}

	public CompanyDetails update(CompanyDetails companyDetails) {
		Date date = new Date();
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
		existingCompanyDetails.setUpdatedDate(date);
		return companyDetailsRepository.save(existingCompanyDetails);
	}

	public CompanyDetails softDelete(Long id, String updatedBy) {
		Date date = new Date();
		CompanyDetails existingCompanyDetails = companyDetailsRepository.findById(id).get();
		existingCompanyDetails.setIsDeleted(true);
		existingCompanyDetails.setUpdatedBy(updatedBy);
		existingCompanyDetails.setUpdatedDate(date);
		return companyDetailsRepository.save(existingCompanyDetails);
	}

}
