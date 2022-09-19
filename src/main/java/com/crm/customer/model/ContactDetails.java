package com.crm.customer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CRM_CONTACT_DETAILS")
public class ContactDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONTACT_DETAILS_ID")
	private Long contactDetailsId;
	
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "MIDDEL_NAME")
	private String middelName;

	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "MARITAL_STATUS")
	private String maritalStatus;

	@Column(name = "PREFERRED_LANGUAGE")
	private String preferredLanguage;

	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;
	
	@Column(name = "Fax")
	private String fax;
	
	@Column(name = "NATIONALITY")
	private String nationality;
	
	@Column(name = "STATUS")
	private String status;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "CREATED_BY")
	private Long createdBy;

	@Column(name = "CREATION_DATE")
	private Date creationDate;

	@Column(name = "LAST_UPDATE_LOGIN")
	private Long lastUpdateLogin;

	@Column(name = "LAST_UPDATED_BY")
	private Long lastUpdatedBy;

	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
    private Customer customer;
	
	
}
