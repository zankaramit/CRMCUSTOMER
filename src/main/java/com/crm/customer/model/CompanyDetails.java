package com.crm.customer.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "crm_customer_company_details")
public class CompanyDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_details_id")
	private Long companyDetailsId;
	
	@Column(name = "account_name")
	private String accountName;
	
	@Column(name = "company_registration_number")
	private String companyRegistrationNumber;
	
	@Column(name = "tax_registration_number")
	private String taxRegistrationNumber;
	
	@Column(name = "line_of_business")
	private String lineOfBusiness;
	
	@Column(name = "size_of_company")
	private Long sizeOfCompany;
	
	@Column(name = "annual_revenue")
	private String annualRevenue;
	
	@Column(name = "website_details")
	private String websiteDetails;
	
	@Column(name = "parent_account")
	private String parentAccount;
	
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

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Customer customer;
	

	
}
