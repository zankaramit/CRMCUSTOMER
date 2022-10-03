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

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Data
@ToString
@Table(name = "crm_occupation_details")
public class OccupationDetails {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "occupation_details_id")
	private Long occupationDetailsId;

	@Column(name = "current_occupation")
	private String currentOccupation;
	
	@Column(name = "current_occupation_years")
	private String currentOccupationYears;
	
	@Column(name = "current_occupation_month")
	private String currentOccupationMonth;
	
	@Column(name = "last_education_level")
	private String lastEducationLevel;
	
	@Column(name = "hobby")
	private String hobby;
	
	@Column(name = "num_persons_household")
	private Long numPersonsHousehold;
	
	@Column(name = "income_group")
	private String incomeGroup;
	
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
	
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Customer customer;
	
}
