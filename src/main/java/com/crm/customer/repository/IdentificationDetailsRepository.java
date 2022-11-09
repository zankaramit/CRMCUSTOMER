package com.crm.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.crm.customer.model.Identification;

public interface IdentificationDetailsRepository extends RevisionRepository<Identification, Long, Long>,
		JpaRepository<Identification, Long>, PagingAndSortingRepository<Identification, Long> {

	Optional<Identification> findByCustomerCustomerIdAndIsDeleted(Long id, boolean b);

	Page<Identification> findByIsDeletedAndOwnerInAndCustomerCustomerId(boolean b, List<String> checkAccessApi,
			Long customerId, Pageable pageable);

	Page<Identification> findByIsDeletedAndOwnerInAndCustomerCustomerIdAndIdentificationNumberLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndIdentificationTypeLikeIgnoreCase(
			boolean b, List<String> checkAccessApi, Long customerId, String string, boolean c,
			List<String> checkAccessApi2, Long customerId2, String string2, Pageable pageable);

}
