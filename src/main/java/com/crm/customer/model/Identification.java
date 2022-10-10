package com.crm.customer.model;

import java.time.LocalDate;
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
@Table(name = "crm_identification_details")
public class Identification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "identification_id")
	private Long identificationId;

	@Column(name = "identification_type")
	private String identificationType;

	@Column(name = "identification_number")
	private String identificationNumber;

	@Column(name = "id_expiry_date")
	private LocalDate idExpiryDate;

	@Column(name = "id_softcopy")
	private String iDSoftcopy;

	@Column(name = "mothers_maiden_name")
	private String mothersMaidenName;

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
