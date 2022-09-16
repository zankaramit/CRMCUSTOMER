package com.crm.customer.service;

import org.springframework.data.domain.Page;

import com.crm.customer.dto.SearchDataTable;
import com.crm.customer.dto.Status;
import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.Customer;

public interface CustomerService {

	Page<Customer> findAll(SearchDataTable searchDataTable);

	Status customerFindById(Long customerId) throws ResourceNotFoundException;

	Status save(Customer customer);

	Status update(Customer customer)throws ResourceNotFoundException;


}
