package com.crm.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.crm.customer.model.ContactDetails;

public interface ContactDetailsRepository
		extends JpaRepository<ContactDetails, Long>, PagingAndSortingRepository<ContactDetails, Long> {

	Optional<ContactDetails> findByContactDetailsIdAndIsDeleted(Long id, boolean b);

	Page<ContactDetails> findByIsDeletedAndCustomerCustomerId(boolean b, Long customerId, Pageable pageable);

	Page<ContactDetails> findByIsDeleted(boolean b, Pageable pageable);

	Page<ContactDetails> findByIsDeletedAndFirstNameLikeIgnoreCaseOrIsDeletedAndMiddelNameLikeIgnoreCaseOrIsDeletedAndLastNameLikeIgnoreCaseOrIsDeletedAndMobileNumberLikeIgnoreCaseOrIsDeletedAndFaxLikeIgnoreCaseOrIsDeletedAndNationalityLikeIgnoreCase(
			boolean b, String string, boolean c, String string2, boolean d, String string3, boolean e, String string4,
			boolean f, String string5, boolean g, String string6, Pageable pageable);

}
