package com.crm.customer.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "credit_card_payment_details")
public class CreditCardPaymentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "creditCardPaymentId")
	private Long creditCardPaymentId;

	@Column(name = "credit_card_issuer")
	private String creditCardIssuer;

	@Column(name = "credit_card_name")
	private String creditCardName;

	@Column(name = "credit_card_type")
	private String creditCardType;

	@Column(name = "credit_card_no")
	private String creditCardNo;

	@Column(name = "credit_card_expiry")
	private LocalDate creditCardExpiry;
	
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
	
//	@JsonIgnoreProperties({ "invoiceDetails","creditCardPaymentDetails","bankDetails" })
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "billing_account_id")
	private BillingAccount billingAccount;
}
