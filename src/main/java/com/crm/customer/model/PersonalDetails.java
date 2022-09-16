package com.crm.customer.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "crm_personal_details")
public class PersonalDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long personalDetailsID;
	
	@Column(name = "first_name")
	private String firstName;		
	
	@Column(name = "middel_name")
	private String middelName;
	
	@Column(name = "last_name")
	private String lastName;	
	
	@Column(name = "gender")
	private String gender;		
	
	@Column(name = "marital_status")
	private String maritalStatus;		
	
	@Column(name = "preferred_language")
	private String preferredLanguage;		
	
	@Column(name = "mobile_number")
	private String mobileNumber;		
	
	@Column(name = "email_address")
	private String emailAddress;		
	
	@Column(name = "parent_account")
	private String parentAccount;		
	
	@Column(name = "fax_no")
	private String faxNo;		
	
	@Column(name = "dob")
	private Date dob;		
	
	@Column(name = "nationality")
	private String nationality;		
	
	@Column(name = "placeOfBirth")
	private String placeOfBirth;		
	
	@Column(name = "religion")
	private String religion;	
	
	@Column(name = "status")
	private String status;		
	
	@Column(name = "start_date")
	private Date startDate;		
	
	@Column(name = "end_date")
	private Date endDate;		
	
	@Column(name = "created_by")
	private Long createdBy;		
	
	@Column(name = "creation_date")
	private Date creationDate;		
	
	@Column(name = "last_update_login")
	private Long lastUpdateLogin;		
	
	@Column(name = "last_updated_by")
	private Long lastUpdatedBy;		
	
	@Column(name = "last_update_date")
	private Date lastUpdateDate;
	
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "customer_id")
//	private Customer customer;


	public PersonalDetails(Long personalDetailsID, String firstName, String middelName, String lastName, String gender,
			String maritalStatus, String preferredLanguage, String mobileNumber, String emailAddress,
			String parentAccount, String faxNo, Date dob, String nationality, String placeOfBirth, String religion,
			String status, Date startDate, Date endDate, Long createdBy, Date creationDate, Long lastUpdateLogin,
			Long lastUpdatedBy, Date lastUpdateDate, Customer customer) {
		super();
		this.personalDetailsID = personalDetailsID;
		this.firstName = firstName;
		this.middelName = middelName;
		this.lastName = lastName;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.preferredLanguage = preferredLanguage;
		this.mobileNumber = mobileNumber;
		this.emailAddress = emailAddress;
		this.parentAccount = parentAccount;
		this.faxNo = faxNo;
		this.dob = dob;
		this.nationality = nationality;
		this.placeOfBirth = placeOfBirth;
		this.religion = religion;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdateLogin = lastUpdateLogin;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
//		this.customer = customer;
	}


	public Long getPersonalDetailsID() {
		return personalDetailsID;
	}


	public void setPersonalDetailsID(Long personalDetailsID) {
		this.personalDetailsID = personalDetailsID;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getMiddelName() {
		return middelName;
	}


	public void setMiddelName(String middelName) {
		this.middelName = middelName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getMaritalStatus() {
		return maritalStatus;
	}


	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


	public String getPreferredLanguage() {
		return preferredLanguage;
	}


	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public String getParentAccount() {
		return parentAccount;
	}


	public void setParentAccount(String parentAccount) {
		this.parentAccount = parentAccount;
	}


	public String getFaxNo() {
		return faxNo;
	}


	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public String getPlaceOfBirth() {
		return placeOfBirth;
	}


	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}


	public String getReligion() {
		return religion;
	}


	public void setReligion(String religion) {
		this.religion = religion;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Long getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public Long getLastUpdateLogin() {
		return lastUpdateLogin;
	}


	public void setLastUpdateLogin(Long lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}


	public Long getLastUpdatedBy() {
		return lastUpdatedBy;
	}


	public void setLastUpdatedBy(Long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}


	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}


	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}


//	public Customer getCustomer() {
//		return customer;
//	}
//
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
//	
	
	
}
