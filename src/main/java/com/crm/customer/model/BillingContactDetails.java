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
@Table(name = "billing_contact_details")
public class BillingContactDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contact_details_id")
	private Long contactDetailsId;

	@Column(name ="account_name")
	private String accountName;

	@Column(name ="service_type")
	private String serviceType;

	@Column(name ="address_type")
	private String addressType;

	@Column(name = "address1")
	private String address1;
	
	@Column(name = "address2")
	private String address2;

	@Column(name ="city")
	private String city;

	@Column(name ="zip_code")
	private String zipCode;

	@Column(name ="province_state")
	private String provinceState;

	@Column(name ="country")
	private String country;

	@Column(name ="comments")
	private String comments;

	@Column(name ="ownership_status")
	private String ownershipStatus;

	@Column(name ="addres_stenure_years")
	private String addressTenureYears;

	@Column(name ="address_tenure_months")
	private String addressTenureMonths;

	@Column(name ="gender")
	private String gender;

	@Column(name ="mobile")
	private String mobile;

	@Column(name ="fax")
	private String fax;

	@Column(name ="email")
	private String email;

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
	@JoinColumn(name = "billing_account_id")
	private BillingAccount billingAccount;
}
