package com.crm.customer.service;

import java.time.LocalDateTime;
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

import com.crm.customer.dto.SearchCustomer;
import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.Customer;
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

		return customerRepository.searchByInput(false, pageable);

	}

}
