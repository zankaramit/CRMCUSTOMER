package com.crm.customer.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.crm.customer.model.AddressDetails;

public interface AddressDetailsRepository
		extends RevisionRepository<AddressDetails, Long, Long>, JpaRepository<AddressDetails, Long>, PagingAndSortingRepository<AddressDetails, Long> {

	Optional<AddressDetails> findByAddressDetailsIdAndIsDeleted(Long id, boolean b);

	Page<AddressDetails> findByIsDeletedAndCustomerCustomerId(boolean b, Long customerId, Pageable pageable);

	Page<AddressDetails> findByIsDeletedAndCustomerCustomerIdAndAddress1LikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndAddress2LikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndAddressTypeLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndCityLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndContactAddressLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndCountryLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndStateLikeIgnoreCase(
			boolean b, Long customerId, String string, boolean c, Long customerId2, String string2, boolean d,
			Long customerId3, String string3, boolean e, Long customerId4, String string4, boolean f, Long customerId5,
			String string5, boolean g, Long customerId6, String string6, boolean h, Long customerId7, String string7,
			Pageable pageable);

	

}
