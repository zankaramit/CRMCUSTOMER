package com.crm.customer.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Data
@Audited
@ToString
@Table(name = "billing_account")
public class BillingAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "billing_account_id")
	private Long billingAccountId;

	@Column(name = "billing_account")
	private String billingAccount;

	@Column(name = "billing_cycle")
	private String billingCycle;

	@Column(name = "account_type")
	private String accountType;

	@Column(name = "service_type")
	private String serviceType;

	@Column(name = "credit_rating")
	private String creditRating;

	@Column(name = "credit_limit")
	private String creditLimit;

	@Column(name = "override_dunning")
	private String overrideDunning;

	@Column(name = "account_class")
	private String accountClass;

	@Column(name = "account_status")
	private String accountStatus;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "billing_period")
	private String billingPeriod;

	@Column(name = "billing_format")
	private String billingFormat;

	@Column(name = "detailed_billing")
	private String detailedBilling;

	@Column(name = "address_type")
	private String addressType;

	@Column(name = "address1")
	private String address1;
	
	@Column(name = "address2")
	private String address2;

	@Column(name = "city")
	private String city;

	@Column(name = "zipcode")
	private String zipCode;

	@Column(name = "province_state")
	private String provinceState;

	@Column(name = "country")
	private String country;
	
	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "updated_date")
	private LocalDateTime updatedDate; 
	
	@Column(name = "owner")
	private String owner; 
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
}
