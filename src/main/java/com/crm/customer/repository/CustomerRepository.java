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

	Page<Customer> findByIsDeletedAndFirstNameLikeIgnoreCaseOrIsDeletedAndMiddelNameLikeIgnoreCaseOrIsDeletedAndLastNameLikeIgnoreCaseOrIsDeletedAndEmailAddressLikeIgnoreCaseOrIsDeletedAndMobileNumberLikeIgnoreCase(
			boolean b, String string, boolean c, String string2, boolean d, String string3, boolean e, String string4,
			boolean f, String string5, Pageable pageable);

}
