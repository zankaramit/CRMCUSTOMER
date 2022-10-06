package com.crm.customer.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.crm.customer.model.ReferenceDetails;

public interface ReferenceDetailsRepository
		extends JpaRepository<ReferenceDetails, Long>, PagingAndSortingRepository<ReferenceDetails, Long> {

	Optional<ReferenceDetails> findByReferenceDetailsIdAndIsDeleted(Long id, boolean b);

	Page<ReferenceDetails> findByIsDeletedAndCustomerCustomerId(boolean b, Long customerId, Pageable pageable);

	Page<ReferenceDetails> findByIsDeletedAndCustomerCustomerIdAndFirstNameLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndMiddelNameLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndLastNameLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndAddressTypeLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndAddress1LikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndAddress2LikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndCityLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndProvinceStateLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndCountryLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndReferencePhoneNumberLikeIgnoreCase(
			boolean b, Long customerId, String string, boolean c, Long customerId2, String string2, boolean d,
			Long customerId3, String string3, boolean e, Long customerId4, String string4, boolean f, Long customerId5,
			String string5, boolean g, Long customerId6, String string6, boolean h, Long customerId7, String string7,
			boolean i, Long customerId8, String string8, boolean j, Long customerId9, String string9, boolean k,
			Long customerId10, String string10, Pageable pageable);

}
