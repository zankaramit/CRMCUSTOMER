package com.crm.customer.service;

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

		return companyDetailsRepository.save(companyDetails);

	}

	public Optional<CompanyDetails> companyDetailsFindById(Long customerId) {

		Optional<CompanyDetails> companyDetails = companyDetailsRepository.findByCustomerCustomerId(customerId);
		return companyDetails;
	}

	public CompanyDetails update(CompanyDetails companyDetails) {
		CompanyDetails companyDetailsUpdate = companyDetailsRepository.findById(companyDetails.getCompanyDetailsId())
				.get();
		companyDetailsUpdate.setAccountName(companyDetails.getAccountName());
		companyDetailsUpdate.setCompanyRegistrationNumber(companyDetails.getCompanyRegistrationNumber());
		companyDetailsUpdate.setTaxRegistrationNumber(companyDetails.getTaxRegistrationNumber());
		companyDetailsUpdate.setLineOfBusiness(companyDetails.getLineOfBusiness());
		companyDetailsUpdate.setSizeOfCompany(companyDetails.getSizeOfCompany());
		companyDetailsUpdate.setAnnualRevenue(companyDetails.getAnnualRevenue());
		companyDetailsUpdate.setWebsiteDetails(companyDetails.getWebsiteDetails());
		companyDetailsUpdate.setParentAccount(companyDetails.getParentAccount());
		companyDetailsUpdate.setStatus("ACTIVE");
		companyDetailsUpdate.setLastUpdateLogin(null);
		companyDetailsUpdate.setLastUpdatedBy(null);
		companyDetailsUpdate.setLastUpdateDate(null);
		return companyDetailsRepository.save(companyDetailsUpdate);
	}

}
