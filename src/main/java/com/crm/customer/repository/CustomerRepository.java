package com.crm.customer.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.crm.customer.model.Customer;

public interface CustomerRepository extends RevisionRepository<Customer, Long, Long>, JpaRepository<Customer, Long>, PagingAndSortingRepository<Customer, Long> {

	Optional<Customer> findByCustomerIdAndIsDeleted(Long id, boolean b);

	Page<Customer> findByIsDeleted(boolean b, Pageable pageable);

	Page<Customer> findByIsDeletedAndFirstNameLikeIgnoreCaseOrIsDeletedAndMiddelNameLikeIgnoreCaseOrIsDeletedAndLastNameLikeIgnoreCaseOrIsDeletedAndEmailAddressLikeIgnoreCaseOrIsDeletedAndMobileNumberLikeIgnoreCaseOrIsDeletedAndAccountNameLikeIgnoreCaseOrIsDeletedAndCompanyRegistrationNumberLikeIgnoreCaseOrIsDeletedAndWebsiteDetailsLikeIgnoreCase(
			boolean b, String string, boolean c, String string2, boolean d, String string3, boolean e, String string4,
			boolean f, String string5, boolean g, String string6, boolean h, String string7, boolean i, String string8,
			Pageable pageable);

}
