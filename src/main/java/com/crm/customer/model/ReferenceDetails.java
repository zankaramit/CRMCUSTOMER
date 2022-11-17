package com.crm.customer.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "REFERENCE_DETAILS")
public class ReferenceDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REFERENCE_DETAILS_ID")
	private Long referenceDetailsId;

	@Column(name = "title")
	private String title;
	
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