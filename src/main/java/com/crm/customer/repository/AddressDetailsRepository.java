package com.crm.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.crm.customer.model.AddressDetails;

public interface AddressDetailsRepository
		extends JpaRepository<AddressDetails, Long>, PagingAndSortingRepository<AddressDetails, Long> {

	Optional<AddressDetails> findByAddressDetailsIdAndIsDeleted(Long id, boolean b);

	Page<AddressDetails> findByIsDeletedAndCustomerCustomerId(boolean b, Long customerId, Pageable pageable);

	Page<AddressDetails> findByIsDeletedAndCustomerCustomerIdAndAddress1LikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndAddress2LikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndAddressTypeLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndContactAddressLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndCountryLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndStateLikeIgnoreCase(
			boolean b, Long customerId, String string, boolean c, Long customerId2, String string2, boolean d,
			Long customerId3, String string3, boolean e, Long customerId4, String string4, boolean f, Long customerId5,
			String string5, boolean g, Long customerId6, String string6, Pageable pageable);

}
