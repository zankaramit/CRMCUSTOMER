package com.crm.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.crm.customer.model.AddressDetails;

public interface AddressDetailsRepository extends RevisionRepository<AddressDetails, Long, Long>,
		JpaRepository<AddressDetails, Long>, PagingAndSortingRepository<AddressDetails, Long> {

	Optional<AddressDetails> findByCustomerCustomerIdAndIsDeleted(Long id, boolean b);

	Page<AddressDetails> findByIsDeletedAndOwnerInAndCustomerCustomerIdAndAddress1LikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndAddress2LikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndAddressTypeLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndCityLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndContactAddressLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndCountryLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndStateLikeIgnoreCase(
			boolean b, List<String> checkAccessApi, Long customerId, String string, boolean c,
			List<String> checkAccessApi2, Long customerId2, String string2, boolean d, List<String> checkAccessApi3,
			Long customerId3, String string3, boolean e, List<String> checkAccessApi4, Long customerId4, String string4,
			boolean f, List<String> checkAccessApi5, Long customerId5, String string5, boolean g,
			List<String> checkAccessApi6, Long customerId6, String string6, boolean h, List<String> checkAccessApi7,
			Long customerId7, String string7, Pageable pageable);

	Page<AddressDetails> findByIsDeletedAndOwnerInAndCustomerCustomerId(boolean b, List<String> checkAccessApi,
			Long customerId, Pageable pageable);

	

	

}
