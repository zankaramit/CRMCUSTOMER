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

	Page<AddressDetails> findByIsDeleted(boolean b, Pageable pageable);

	Page<AddressDetails> findByIsDeletedAndAddress1LikeIgnoreCaseOrIsDeletedAndAddress2LikeIgnoreCaseOrIsDeletedAndAddressTypeLikeIgnoreCaseOrIsDeletedAndContactAddressLikeIgnoreCaseOrIsDeletedAndCountryLikeIgnoreCaseOrIsDeletedAndStateLikeIgnoreCase(
			boolean b, String string, boolean c, String string2, boolean d, String string3, boolean e, String string4,
			boolean f, String string5, boolean g, String string6, Pageable pageable);

	

	Page<AddressDetails> findByIsDeletedAndCustomerCustomerId(boolean b, Long customerId, Pageable pageable);

}
