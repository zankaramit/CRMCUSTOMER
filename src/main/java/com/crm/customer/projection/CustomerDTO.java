package com.crm.customer.projection;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;

public interface CustomerDTO {

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
	
	String getLineOfBusiness();
	
	String getMaritalStatus();

	@Value("#{(target.identification)==null?null:(target.identification.identificationId)}")
	String getIdentificationId();

	@Value("#{(target.identification)==null?null:(target.identification.identificationNumber)}")
	String getIdentificationNumber();

	@Value("#{(target.identification)==null?null:(target.identification.identificationType)}")
	String getIdentificationType();

	@Value("#{(target.identification)==null?null:(target.identification.idExpiryDate).toString()}")
	LocalDate getIdExpiryDate();

	@Value("#{(target.addressDetails)==null?null:(target.addressDetails.addressDetailsId)}")
	Long getAddressDetailsId();

	@Value("#{(target.addressDetails)==null?null:(target.addressDetails.addressType)}")
	String getAddressType();

	@Value("#{(target.addressDetails)==null?null:(target.addressDetails.address1)}")
	String getAddress1();

	@Value("#{(target.addressDetails)==null?null:(target.addressDetails.address2)}")
	String getAddress2();

	@Value("#{(target.addressDetails)==null?null:(target.addressDetails.city)}")
	String getCity();

	@Value("#{(target.addressDetails)==null?null:(target.addressDetails.state)}")
	String getState();

	@Value("#{(target.addressDetails)==null?null:(target.addressDetails.postCode)}")
	String getPostCode();

	@Value("#{(target.addressDetails)==null?null:(target.addressDetails.country)}")
	String getCountry();

}
