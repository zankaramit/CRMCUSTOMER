package com.crm.customer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "crm_customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long customerId;
	
	@Column(name = "customer_type")
	private String customerType;		
	
	@Column(name = "customer_class")
	private String customerClass;		
	
	@Column(name = "customer_name")
	private String customerName;		
	
	@Column(name = "customer_email")
	private String customerEmail;		
	
	@Column(name = "customer_phone")
	private String customerPhone;		
	
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
	
	public Customer() {}

	public Customer(Long customerId, String customerType, String customerClass, String customerName,
			String customerEmail, String phone, String status, Date startDate, Date endDate, Long createdBy,
			Date creationDate, Long lastUpdateLogin, Long lastUpdatedBy, Date lastUpdateDate) {
	
		this.customerId = customerId;
		this.customerType = customerType;
		this.customerClass = customerClass;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerPhone = phone;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdateLogin = lastUpdateLogin;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerClass() {
		return customerClass;
	}

	public void setCustomerClass(String customerClass) {
		this.customerClass = customerClass;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getPhone() {
		return customerPhone;
	}

	public void setPhone(String phone) {
		this.customerPhone = phone;
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

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerType=" + customerType + ", customerClass="
				+ customerClass + ", customerName=" + customerName + ", customerEmail=" + customerEmail + ", phone="
				+ customerPhone + ", status=" + status + ", startDate=" + startDate + ", endDate=" + endDate + ", createdBy="
				+ createdBy + ", creationDate=" + creationDate + ", lastUpdateLogin=" + lastUpdateLogin
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate + "]";
	}		
			


}
