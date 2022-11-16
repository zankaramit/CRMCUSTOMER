package com.crm.customer.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

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
@Table(name = "collaterals")
public class Collaterals {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "collaterals_id")
	private Long collateralsId;

	@Column(name = "reference_id")
	private Long referenceId;

	@Column(name = "flag")
	private String flag;

	@Column(name = "document_type")
	private String documentType;
	
	@Column(name = "document_info")
	private String documentInfo;
	
	@Column(name = "file_name",columnDefinition="TEXT" )
	private String fileName;

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

}
