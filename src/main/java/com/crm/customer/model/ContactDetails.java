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
	
	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;
	
	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "post_Code")
	private String postCode;


	@Column(name = "NATIONALITY")
	private String nationality;

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

	@NotAudited
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

}
