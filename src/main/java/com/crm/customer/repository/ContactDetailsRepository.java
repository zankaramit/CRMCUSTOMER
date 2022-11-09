package com.crm.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.crm.customer.model.ContactDetails;

public interface ContactDetailsRepository extends RevisionRepository<ContactDetails, Long, Long>,
		JpaRepository<ContactDetails, Long>, PagingAndSortingRepository<ContactDetails, Long> {

	Optional<ContactDetails> findByContactDetailsIdAndIsDeleted(Long id, boolean b);

	Page<ContactDetails> findByIsDeletedAndOwnerInAndCustomerCustomerIdAndFirstNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndMiddelNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndLastNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndMobileNumberLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndFaxLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndNationalityLikeIgnoreCase(
			boolean b, List<String> checkAccessApi, Long customerId, String string, boolean c,
			List<String> checkAccessApi2, Long customerId2, String string2, boolean d, List<String> checkAccessApi3,
			Long customerId3, String string3, boolean e, List<String> checkAccessApi4, Long customerId4, String string4,
			boolean f, List<String> checkAccessApi5, Long customerId5, String string5, boolean g,
			List<String> checkAccessApi6, Long customerId6, String string6, Pageable pageable);

	Page<ContactDetails> findByIsDeletedAndOwnerInAndCustomerCustomerId(boolean b, List<String> checkAccessApi,
			Long customerId, Pageable pageable);

}
