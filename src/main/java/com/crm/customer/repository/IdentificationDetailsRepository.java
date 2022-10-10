package com.crm.customer.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.crm.customer.model.Identification;

public interface IdentificationDetailsRepository extends RevisionRepository<Identification, Long, Long>, JpaRepository<Identification, Long>, PagingAndSortingRepository<Identification, Long> {

	Optional<Identification> findByIdentificationIdAndIsDeleted(Long id, boolean b);

	Page<Identification> findByIsDeletedAndCustomerCustomerIdAndIdentificationNumberLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndIdentificationTypeLikeIgnoreCase(
			boolean b, Long customerId, String string, boolean c, Long customerId2, String string2, Pageable pageable);

	Page<Identification> findByIsDeletedAndCustomerCustomerId(boolean b, Long customerId, Pageable pageable);

	

	

}
