package com.crm.customer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Sort;

import com.crm.customer.dto.SearchDataTable;
import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.Customer;
import com.crm.customer.repository.CustomerRepository;

@Service
public class CustomerService  {

	@Autowired
	CustomerRepository customerRepository;

	
	public Page<Customer> findAll(SearchDataTable searchDataTable) {
		Pageable p = PageRequest.of(searchDataTable.getPage(), searchDataTable.getSize(),
				Sort.by(Sort.Direction.DESC, searchDataTable.getSortby()));

		return  customerRepository
				.findByFirstNameLikeIgnoreCaseOrEmailAddressLikeIgnoreCaseOrMobileNumberLikeIgnoreCase(
						"%" + searchDataTable.getSearchField() + "%", "%" + searchDataTable.getSearchField() + "%",
						"%" + searchDataTable.getSearchField() + "%", p);
		
	}

	
	public Optional<Customer> customerFindById(Long customerId) {

		Optional<Customer> customer = customerRepository.findById(customerId);

		return customer;
	}

	
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	
	public Customer update(Customer customer) {

		Customer ustomerUpadte = customerRepository.findById(customer.getCustomerId()).get();
		ustomerUpadte.setCustomerType(customer.getCustomerType());
		ustomerUpadte.setCustomerClass(customer.getCustomerClass());
		ustomerUpadte.setFirstName(customer.getFirstName());
		ustomerUpadte.setMiddelName(customer.getMiddelName());
		ustomerUpadte.setLastName(customer.getLastName());
		ustomerUpadte.setGender(customer.getGender());
		ustomerUpadte.setMaritalStatus(customer.getMaritalStatus());
		ustomerUpadte.setPreferredLanguage(customer.getPreferredLanguage());
		ustomerUpadte.setMobileNumber(customer.getMobileNumber());
		ustomerUpadte.setEmailAddress(customer.getEmailAddress());
		ustomerUpadte.setParentAccount(customer.getParentAccount());
		ustomerUpadte.setFax(customer.getFax());
		ustomerUpadte.setNationality(customer.getNationality());
		ustomerUpadte.setDateOfBirth(customer.getDateOfBirth());
		ustomerUpadte.setPlaceOfBirth(customer.getPlaceOfBirth());
		return customerRepository.save(ustomerUpadte);

	}

}
