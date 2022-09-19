package com.crm.customer.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Data
@ToString
@Table(name = "crm_company_address_details")
public class CompanyAddressDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_details_id")
	private Long addressDetailsId;

	@Column(name = "address_type")
	private String addressType;

	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;

	@Column(name = "contact_address")
	private String contactAddress;

	@Column(name = "state")
	private String state;

	@Column(name = "post_Code")
	private String postCode;

	@Column(name = "country")
	private String country;

	@Column(name = "ownership_status")
	private String ownershipStatus;

	@Column(name = "address_tenure_years")
	private Long addressTenureYears;

	@Column(name = "address_tenure_month")
	private Long addressTenureMonth;

	@Column(name = "status")
	private String status;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "creation_date")
	private Date creationDate;

	@Column(name = "last_update_login")
	private Long lastUpdateLogin;

	@Column(name = "last_updated_by")
	private Long lastUpdatedBy;

	@Column(name = "last_update_date")
	private Date lastUpdateDate;
	

	
	@ManyToOne
	@JoinColumn(name = "customer_id")
    private Customer customer;
	
	
//	@OneToOne(cascade = CascadeType.MERGE)
//	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
//	private Customer customer;
	
//	@ManyToOne
//	@JoinColumn(name = "company_details_id")
//    private CompanyDetails companyDetails;
}
