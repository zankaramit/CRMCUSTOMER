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
@Table(name = "REFERENCE_DETAILS")
public class ReferenceDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REFERENCE_DETAILS_ID")
	private Long referenceDetailsId;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "MIDDEL_NAME")
	private String middelName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "ADDRESS_TYPE")
	private String addressType;
	
	@Column(name = "ADDRESS1")
	private String address1;
	
	@Column(name = "ADDRESS2")
	private String address2;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "PROVINCE_STATE")
	private String provinceState;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "POST_CODE")
	private Long postCode;
	
	@Column(name = "REFERENCE_PHONE_NUMBER")
	private String referencePhoneNumber;
	
	@Column(name = "RELATIONSHIP")
	private String relationship;
	
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
