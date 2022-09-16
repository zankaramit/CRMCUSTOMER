package com.crm.customer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private Date dateOfBirth;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "place_of_birth")
	private String placeOfBirth;

	@Column(name = "religion")
	private String religion;

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

}
