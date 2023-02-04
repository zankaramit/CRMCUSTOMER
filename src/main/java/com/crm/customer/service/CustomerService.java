package com.crm.customer.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.crm.customer.dto.CustomerServiceRequest;
import com.crm.customer.dto.SearchCustomer;
import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.BillingAccount;
import com.crm.customer.model.Collaterals;
import com.crm.customer.model.Customer;
import com.crm.customer.projection.BillingAddressDTO;
import com.crm.customer.projection.BillingDTO;
import com.crm.customer.projection.CustomerDTO;
import com.crm.customer.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	UserService userService;

	@Autowired
	UploadFileService uploadFileService;

	@Autowired
	BillingAccountService billingAccountService;

	@Autowired
	CollateralsService collateralsService;

	public Page<Customer> getByNameCustomerAndPagination(String name, String owner, Pageable pageable) {

		List<String> checkAccessApi = userService.checkAccessApi(owner);
		Page<Customer> customer = null;

		if (ObjectUtils.isEmpty(name)) {
			customer = customerRepository.findByIsDeletedAndOwnerIn(false, checkAccessApi, pageable);
		} else {
			customer = customerRepository
					.findByIsDeletedAndOwnerInAndFirstNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndMiddelNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndLastNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndEmailAddressLikeIgnoreCaseOrIsDeletedAndOwnerInAndMobileNumberLikeIgnoreCaseOrIsDeletedAndOwnerInAndAccountNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndCompanyRegistrationNumberLikeIgnoreCaseOrIsDeletedAndOwnerInAndWebsiteDetailsLikeIgnoreCase(
							false, checkAccessApi, "%" + name + "%", false, checkAccessApi, "%" + name + "%", false,
							checkAccessApi, "%" + name + "%", false, checkAccessApi, "%" + name + "%", false,
							checkAccessApi, "%" + name + "%", false, checkAccessApi, "%" + name + "%", false,
							checkAccessApi, "%" + name + "%", false, checkAccessApi, "%" + name + "%", pageable);
		}
		return customer;
	}

	public Optional<Customer> getById(Long id) {
		return customerRepository.findByCustomerIdAndIsDeleted(id, false);

	}

	public Customer save(Customer customer, MultipartFile file) {
		if (file != null) {
			if (!file.getOriginalFilename().isEmpty()) {
				if (!file.isEmpty()) {
					String fileUploadLocation = uploadFileService.uploadFile(file);
					customer.setProfilePhoto(fileUploadLocation);
				}
			}
		}
		if (customer.getParentAccount().getCustomerId() == null) {
			customer.setParentAccount(null);
		}
		LocalDateTime dateTime = LocalDateTime.now();
		customer.setIsDeleted(false);
		customer.setCreatedDate(dateTime);
		customer.setOwner(customer.getCreatedBy());
		return customerRepository.save(customer);

	}

	public Customer update(Customer customer, MultipartFile file) {
		LocalDateTime dateTime = LocalDateTime.now();
		Optional<Customer> findById = customerRepository.findById(customer.getCustomerId());
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException("Customer Details not found for ID :" + customer.getCustomerId());
		}
		Customer existingCustomer = findById.get();
		if (Objects.equals(customer.getParentAccount().getCustomerId(), customer.getCustomerId())) {
			throw new ResourceNotFoundException("Unable to select same account as parent account");
		}
		if (file != null) {
			if (!file.getOriginalFilename().isEmpty()) {
				if (!file.isEmpty()) {
					String fileUploadLocation = uploadFileService.uploadFile(file);
					existingCustomer.setProfilePhoto(fileUploadLocation);
				}
			}
		}
		existingCustomer.setCustomerType(customer.getCustomerType());
		existingCustomer.setCustomerClass(customer.getCustomerClass());
		existingCustomer.setTitle(customer.getTitle());
		existingCustomer.setFirstName(customer.getFirstName());
		existingCustomer.setMiddelName(customer.getMiddelName());
		existingCustomer.setLastName(customer.getLastName());
		existingCustomer.setGender(customer.getGender());
		existingCustomer.setMaritalStatus(customer.getMaritalStatus());
		existingCustomer.setPreferredLanguage(customer.getPreferredLanguage());
		existingCustomer.setMobileNumber(customer.getMobileNumber());
		existingCustomer.setEmailAddress(customer.getEmailAddress());
		existingCustomer.setFax(customer.getFax());
		existingCustomer.setNationality(customer.getNationality());
		existingCustomer.setDateOfBirth(customer.getDateOfBirth());
		existingCustomer.setPlaceOfBirth(customer.getPlaceOfBirth());
		existingCustomer.setAccountName(customer.getAccountName());
		existingCustomer.setCompanyRegistrationNumber(customer.getCompanyRegistrationNumber());
		existingCustomer.setTaxRegistrationNumber(customer.getTaxRegistrationNumber());
		existingCustomer.setLineOfBusiness(customer.getLineOfBusiness());
		existingCustomer.setSizeOfCompany(customer.getSizeOfCompany());
		existingCustomer.setAnnualRevenue(customer.getAnnualRevenue());
		existingCustomer.setWebsiteDetails(customer.getWebsiteDetails());
		existingCustomer.setPromotionalMessages(customer.getPromotionalMessages());
		existingCustomer.setReligion(customer.getReligion());
		if (customer.getParentAccount().getCustomerId() == null) {
			existingCustomer.setParentAccount(null);
		} else {
			existingCustomer.setParentAccount(customer.getParentAccount());
		}
		existingCustomer.setUpdatedBy(customer.getUpdatedBy());
		existingCustomer.setUpdatedDate(dateTime);
		return customerRepository.save(existingCustomer);

	}

	public Customer softDelete(Long id, String updatedBy) {
		LocalDateTime dateTime = LocalDateTime.now();
		Customer existingCustomer;
		Optional<Customer> findById = customerRepository.findById(id);
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException("Customer Details not found and ID :" + id);
		}
		existingCustomer = findById.get();
		Boolean b = existingCustomer.getIsDeleted();
		if (Boolean.TRUE.equals(b)) {
			throw new PersistenceException("Customer Details already Deleted and ID :" + id);
		}
		existingCustomer.setIsDeleted(true);
		existingCustomer.setUpdatedBy(updatedBy);
		existingCustomer.setUpdatedDate(dateTime);
		return customerRepository.save(existingCustomer);
	}

	public Page<Customer> getAllByCustomerType(String name, String customerType, Pageable pageable) {
		Page<Customer> consumer = null;

		if (ObjectUtils.isEmpty(name)) {
			consumer = customerRepository.findByIsDeletedAndCustomerTypeIgnoreCase(false, customerType, pageable);
		} else {
			consumer = customerRepository
					.findByIsDeletedAndCustomerTypeAndFirstNameLikeIgnoreCaseOrIsDeletedAndCustomerTypeAndMiddelNameLikeIgnoreCaseOrIsDeletedAndCustomerTypeAndLastNameLikeIgnoreCaseOrIsDeletedAndCustomerTypeAndEmailAddressLikeIgnoreCaseOrIsDeletedAndCustomerTypeAndMobileNumberLikeIgnoreCaseOrIsDeletedAndCustomerTypeAndAccountNameLikeIgnoreCaseOrIsDeletedAndCustomerTypeAndCompanyRegistrationNumberLikeIgnoreCaseOrIsDeletedAndCustomerTypeAndWebsiteDetailsLikeIgnoreCase(
							false, customerType, "%" + name + "%", false, customerType, "%" + name + "%", false,
							customerType, "%" + name + "%", false, customerType, "%" + name + "%", false, customerType,
							"%" + name + "%", false, customerType, "%" + name + "%", false, customerType,
							"%" + name + "%", false, customerType, "%" + name + "%", pageable);
		}
		return consumer;
	}

	public Page<SearchCustomer> searchCustomer(String searchType, String input, Pageable pageable) {

		String documentTypeCollaterals = "COMPANY_REGISTRATION_NUMBER";

		if (searchType.equals("Customer Account")) {
			return customerRepository.searchByConsumerName(false, documentTypeCollaterals, input, pageable);
		}
		if (searchType.equals("Account Name")) {
			return customerRepository.searchByCorporateName(false, documentTypeCollaterals, input, pageable);
		}
		if (searchType.equals("Billing Account")) {
			return customerRepository.searchByBillingAccount(false, documentTypeCollaterals, input, pageable);
		}
		if (searchType.equals("ID Type")) {
			return customerRepository.searchByID(false, documentTypeCollaterals, input, pageable);
		}

		return customerRepository.searchByInput(false, documentTypeCollaterals, input, searchType, pageable);

	}

	public Object geatAllInfo(Long customerId, Long billingAccountId, String callType) {
		Object obj = null;
		switch (callType) {
		case "CHANGE BASIC CUSTOMER PROFILE":
			try {
				CustomerDTO customerDTO = customerRepository.findByCustomerId(customerId);
				obj = customerDTO;
			} catch (Exception e) {
				throw new ResourceNotFoundException("Customer Details not found ");
			}

			break;

		case "CHANGE BILLING PROFILE":
			try {
				BillingDTO billingDTO = billingAccountService.getBilling(billingAccountId);
				obj = billingDTO;
			} catch (Exception e) {
				throw new ResourceNotFoundException("Customer Details not found ");
			}
			break;

		case "CHANGE BILLING ADDRESS":
			try {
				BillingAddressDTO billingAddressDTO = billingAccountService.getBillingAddress(billingAccountId);
				obj = billingAddressDTO;
			} catch (Exception e) {
				throw new ResourceNotFoundException("Customer Details not found ");
			}
			break;

		default:
//			UNBILLED USAGE
//			INVOICE DETAILS
//			OUTSTANDING
//			BROADBAND ACCESS
//			IPTV ACCESS
//			CHANGE SIM CARD
			break;
		}
		return obj;
	}

	public CustomerServiceRequest getCutomerAndBilling(Long customerId, Long billingAccountId) {

		CustomerServiceRequest customerAllDetails = new CustomerServiceRequest();

		Customer customer = null;
		Optional<Customer> byId = getById(customerId);
		customer = byId.get();
		customer.setParentAccount(null);

		List<BillingAccount> billingList = new ArrayList<BillingAccount>();
		BillingAccount billingAccount = null;
		Optional<BillingAccount> byId2 = billingAccountService.getById(billingAccountId);
		billingAccount = byId2.get();
		billingList.add(billingAccount);
		customer.setBillingAccount(billingList);

		customerAllDetails.setCustomer(customer);

		Optional<Collaterals> collateralsOptional = collateralsService.getCollateralsByCustomer(customerId);
		Collaterals collaterals = null;
		if (collateralsOptional.isPresent()) {
			collaterals = collateralsOptional.get();
		}

		customerAllDetails.setCollaterals(collaterals);

		return customerAllDetails;
	}

	public Optional<Customer> findUserId(String userid) {

		return customerRepository.findByIsDeletedAndUserId(false, userid);
	}

}
