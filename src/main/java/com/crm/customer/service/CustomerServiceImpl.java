package com.crm.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Sort;

import com.crm.customer.dto.SearchDataTable;
import com.crm.customer.dto.Status;
import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.Customer;
import com.crm.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Page<Customer> findAll(SearchDataTable searchDataTable) {
		Pageable p = PageRequest.of(searchDataTable.getPage(), searchDataTable.getSize(),
				Sort.by(Sort.Direction.DESC, searchDataTable.getSortby()));

		Page<Customer> list = customerRepository
				.findByCustomerNameLikeIgnoreCaseOrCustomerEmailLikeIgnoreCaseOrCustomerPhoneLikeIgnoreCase(
						"%" + searchDataTable.getSearchField() + "%", "%" + searchDataTable.getSearchField() + "%",
						"%" + searchDataTable.getSearchField() + "%", p);
		return list;
	}

	@Override
	public Status customerFindById(Long customerId) throws ResourceNotFoundException {
		Status status = new Status();
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
		status.setData(customer);
		status.setMessage("Success");
		return status;
	}

	@Override
	public Status save(Customer customer) {
		Status status = new Status();
		customerRepository.save(customer);
		status.setMessage("Success");
		return status;
	}

	@Override
	public Status update(Customer customer) throws ResourceNotFoundException {
		Status status = new Status();
		customerRepository.findById(customer.getCustomerId())
		.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customer.getCustomerId()));
		customerRepository.save(customer);
		status.setMessage("Success");
		return status;
	}

}
