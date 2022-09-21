package com.crm.customer.repository;

import java.util.List;
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

	Page<ReferenceDetails> findByIsDeleted(boolean b, Pageable pageable);

	Page<ReferenceDetails> findByIsDeletedAndFirstNameLikeIgnoreCaseOrIsDeletedAndMiddelNameLikeIgnoreCaseOrIsDeletedAndLastNameLikeIgnoreCaseOrIsDeletedAndAddressTypeLikeIgnoreCaseOrIsDeletedAndAddress1LikeIgnoreCaseOrIsDeletedAndAddress2LikeIgnoreCaseOrIsDeletedAndCityLikeIgnoreCaseOrIsDeletedAndProvinceStateLikeIgnoreCaseOrIsDeletedAndCountryLikeIgnoreCaseOrIsDeletedAndReferencePhoneNumberLikeIgnoreCase(
			boolean b, String string, boolean c, String string2, boolean d, String string3, boolean e, String string4,
			boolean f, String string5, boolean g, String string6, boolean h, String string7, boolean i, String string8,
			boolean j, String string9, boolean k, String string10, Pageable pageable);

}
