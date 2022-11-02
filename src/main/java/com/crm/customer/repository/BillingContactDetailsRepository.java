package com.crm.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.crm.customer.model.BillingContactDetails;

public interface BillingContactDetailsRepository extends RevisionRepository<BillingContactDetails, Long, Long>,  JpaRepository<BillingContactDetails, Long>, PagingAndSortingRepository<BillingContactDetails, Long> {

	Optional<BillingContactDetails> findByContactDetailsIdAndIsDeleted(Long id, boolean b);

	Page<BillingContactDetails> findByIsDeletedAndOwnerIn(boolean b, List<String> checkAccessApi, Pageable pageable);

	Page<BillingContactDetails> findByIsDeletedAndOwnerInAndAccountNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndServiceTypeLikeIgnoreCaseOrIsDeletedAndOwnerInAndMobileLikeIgnoreCaseOrIsDeletedAndOwnerInAndEmailLikeIgnoreCase(
			boolean b, List<String> checkAccessApi, String string, boolean c, List<String> checkAccessApi2,
			String string2, boolean d, List<String> checkAccessApi3, String string3, boolean e,
			List<String> checkAccessApi4, String string4, Pageable pageable);

}
