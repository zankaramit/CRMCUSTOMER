package com.crm.customer.dto;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;

public interface CustomerDTO {
//	String getFirstName();
//	Identification getIdentification();
//	interface Identification{
//		Long getIdentificationId();
//		String getIdentificationNumber();
//	}

	Long getCustomerId();

	String getCustomerClass();

	String getFirstName();

	String getMiddelName();

	String getLastName();

	String getAccountName();

	String getMobileNumber();

	String getEmailAddress();

	LocalDate getDateOfBirth();

	String getNationality();

	Boolean getPromotionalMessages();

	@Value("#{target.identification.identificationId}")
	String getIdentificationId();

	@Value("#{target.identification.identificationNumber}")
	String getIdentificationNumber();
	
	@Value("#{target.identification.identificationType}")
	String getIdentificationType();
	
	@Value("#{target.identification.idExpiryDate}")
	LocalDate getIdExpiryDate();
	
	
	
	

}
