package com.crm.customer.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "crm_customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "customer_type")
	private String customerType;

	@Column(name = "customer_class")
	private String customerClass;
	
	@Column(name = "title")
	private String title;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middel_name")
	private String middelName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "gender")
	private String gender;

	@Column(name = "marital_status")
	private String maritalStatus;

	@Column(name = "preferred_language")
	private String preferredLanguage;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "email_Address")
	private String emailAddress;

	@Column(name = "parent_account")
	private String parentAccount;

	@Column(name = "fax")
	private String fax;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "place_of_birth")
	private String placeOfBirth;

	@Column(name = "religion")
	private String religion;

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

}
