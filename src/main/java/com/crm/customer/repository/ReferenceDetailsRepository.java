package com.crm.customer.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.crm.customer.model.ReferenceDetails;

public interface ReferenceDetailsRepository
		extends JpaRepository<ReferenceDetails, Long>, PagingAndSortingRepository<ReferenceDetails, Long> {

	Page<ReferenceDetails> findByCustomerCustomerId(Long customerId, Pageable p);

	Page<ReferenceDetails> findByFirstNameLikeIgnoreCaseOrMiddelNameLikeIgnoreCaseOrLastNameLikeIgnoreCaseOrAddressTypeLikeIgnoreCaseOrAddress1LikeIgnoreCaseOrAddress2LikeIgnoreCaseOrCityLikeIgnoreCaseOrProvinceStateLikeIgnoreCaseOrCountryLikeIgnoreCaseOrReferencePhoneNumberLikeIgnoreCaseOrRelationshipLikeIgnoreCase(
			String string, String string2, String string3, String string4, String string5, String string6,
			String string7, String string8, String string9, String string10, String string11, Pageable p);

}
