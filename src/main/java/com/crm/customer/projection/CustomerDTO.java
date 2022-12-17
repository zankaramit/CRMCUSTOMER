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

	@Value("#{(target.identification.identificationId)==null?'':(target.identification.identificationId)}")
	String getIdentificationId();

	@Value("#{target.identification.identificationNumber}")
	String getIdentificationNumber();

	@Value("#{target.identification.identificationType}")
	String getIdentificationType();

	@Value("#{target.identification.idExpiryDate}")
	LocalDate getIdExpiryDate();

	@Value("#{(target.addressDetails.addressDetailsId)==null?'':(target.addressDetails.addressDetailsId)}")
	Long getAddressDetailsId();

	@Value("#{target.addressDetails.addressType}")
	String getAddressType();

	@Value("#{target.addressDetails.address1}")
	String getAddress1();

	@Value("#{target.addressDetails.address2}")
	String getAddress2();

	@Value("#{target.addressDetails.city}")
	String getCity();

	@Value("#{target.addressDetails.state}")
	String getState();

	@Value("#{target.addressDetails.postCode}")
	String getPostCode();

	@Value("#{target.addressDetails.country}")
	String getCountry();

}
