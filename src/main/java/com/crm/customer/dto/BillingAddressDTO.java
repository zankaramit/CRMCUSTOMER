package com.crm.customer.dto;

public interface BillingAddressDTO {

	Long getBillingAccountId();
	String getAddressType();
	String getAddress1();
	String getAddress2();
	String getCity();
	String getZipCode();
	String getProvinceState();
	String getCountry();
}
