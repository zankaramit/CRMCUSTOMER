package com.crm.customer.dto;

import com.crm.customer.model.BillingAccount;
import com.crm.customer.model.Collaterals;
import com.crm.customer.model.Customer;
import com.crm.customer.model.Identification;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SearchCustomer {

	private String msisdn;

	@JsonIgnoreProperties({ "billingAccount", "parentAccount", "identification" })
	private Customer customer;

	@JsonIgnoreProperties({ "customer" })
	private BillingAccount billingAccount;

	@JsonIgnoreProperties({ "customer" })
	private Identification identification;

	private Collaterals collaterals;

	public SearchCustomer(Customer customer, BillingAccount billingAccount, Identification identification,
			Collaterals collaterals) {
		super();
		this.customer = customer;
		this.billingAccount = billingAccount;
		this.identification = identification;
		this.collaterals = collaterals;
		this.msisdn = customer.getMobileNumber();

	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn() {
		this.msisdn = customer.getMobileNumber();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collaterals getCollaterals() {
		return collaterals;
	}

	public void setCollaterals(Collaterals collaterals) {
		this.collaterals = collaterals;
	}

	public BillingAccount getBillingAccount() {
		return billingAccount;
	}

	public void setBillingAccount(BillingAccount billingAccount) {
		this.billingAccount = billingAccount;
	}

	public Identification getIdentification() {
		return identification;
	}

	public void setIdentification(Identification identification) {
		this.identification = identification;
	}

}
