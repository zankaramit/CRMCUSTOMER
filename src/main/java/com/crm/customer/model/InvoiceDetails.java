package com.crm.customer.model;

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
@Table(name = "invoice_details")
public class InvoiceDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_details_id")
	private Long invoiceDetailsId;
	
	@Column(name = "payment_mode")
	private String paymentMode;

	@Column(name = "bill_media")
	private String billMedia;

	@Column(name = "no_of_copies")
	private String noOfCopies;

	@Column(name = "preffered_language")
	private String prefferedLanguage;
	
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
	
	@NotAudited
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "billing_account_id", referencedColumnName = "billing_account_id")
	private BillingAccount billingAccount;
}