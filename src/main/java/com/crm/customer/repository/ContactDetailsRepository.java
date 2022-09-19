package com.crm.customer.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.crm.customer.model.ContactDetails;

public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long>, PagingAndSortingRepository<ContactDetails, Long> {

	Page<ContactDetails> findByCustomerCustomerId(Long customerId, Pageable p);

	Page<ContactDetails> findByFirstNameLikeIgnoreCaseOrMiddelNameLikeIgnoreCaseOrLastNameLikeIgnoreCaseOrGenderLikeIgnoreCaseOrMaritalStatusLikeIgnoreCaseOrMobileNumberLikeIgnoreCaseOrFaxLikeIgnoreCaseOrNationalityLikeIgnoreCase(
			String string, String string2, String string3, String string4, String string5, String string6,
			String string7, String string8, Pageable p);

	

}
