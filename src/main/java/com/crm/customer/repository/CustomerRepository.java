package com.crm.customer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.crm.customer.model.Customer;
public interface CustomerRepository extends JpaRepository<Customer, Long>,PagingAndSortingRepository<Customer, Long>{

	

	

	

	Page<Customer> findByCustomerNameLikeIgnoreCaseOrCustomerEmailLikeIgnoreCaseOrCustomerPhoneLikeIgnoreCase(
			String string, String string2, String string3, Pageable p);



}
