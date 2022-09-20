package com.crm.customer.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.crm.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>, PagingAndSortingRepository<Customer, Long> {

	Optional<Customer> findByCustomerIdAndIsDeleted(Long id, boolean b);

	Page<Customer> findByIsDeleted(boolean b, Pageable pageable);

	

	Page<Customer> findByFirstNameLikeIgnoreCaseOrMiddelNameLikeIgnoreCaseOrLastNameLikeIgnoreCaseOrEmailAddressLikeIgnoreCaseOrMobileNumberLikeIgnoreCaseAndIsDeleted(
			String string, String string2, String string3, String string4, String string5, boolean b,
			Pageable pageable);

}
