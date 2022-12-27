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

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "crm_address_details")
public class AddressDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_details_id")
	private Long addressDetailsId;

	@Column(name = "address_type")
	private String addressType;

	@Column(name = "identification_type")
	private String identificationType;

	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;
	
	@Column(name = "city")
	private String city;

	@Column(name = "contact_number")
	private String contactNumber;

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

	@JsonIgnoreProperties({ "identification","billingAccount","addressDetails" })
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id")
	private Customer customer;

}
